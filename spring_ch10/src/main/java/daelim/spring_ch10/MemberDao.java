package daelim.spring_ch10;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberDao {

    private JdbcTemplate jdbcTemplate;

    public MemberDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member(
            rs.getString("EMAIL"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getTimestamp("REGDATE").toLocalDateTime()
                    );
            member.setId(rs.getLong("ID"));
            return member;
        }
    }

    private static long nextId = 0;
    private Map<String, Member> map = new HashMap<>();

    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
               new MemberRowMapper(),
                email
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public Long insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement preparedStatement = con.prepareStatement(
                        "insert into MEMBER(EMAIL, PASSWORD, NAME, REGDATE) values (?,?,?,?)"
                            , new String[] {"ID"}
                );
                preparedStatement.setString(1, member.getEmail());
                preparedStatement.setString(2, member.getPassword());
                preparedStatement.setString(3, member.getName());
                preparedStatement.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
                return preparedStatement;
            }
        }, keyHolder);
        Number keyValue = keyHolder.getKey();
        member.setId(keyValue.longValue());
    }



    public void update(Member member) {
        int updateCount = jdbcTemplate.update(
                "update MEMBER set PASSWORD = ? where EMAIL = ?",
                member.getPassword(), member.getEmail()
        );
        System.out.println("updateCount : " + updateCount);

//        map.put(member.getEmail(), member);
    }

    public List<Member> selectAll() {

        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER",
                new MemberRowMapper()
        );
        return results;
    }

    public int count() {
        Integer count = jdbcTemplate.queryForObject(
                "select count(*) from MEMBER", Integer.class
        );
    return count;
    }

    public List<Member> selectByRegdate(LocalDateTime from, LocalDateTime to) {
        List<Member> results = jdbcTemplate.query(
                "SELECT * FROM MEMBER WHERE REGDATE BETWEEN ? AND ? ORDER BY REGDATE DESC",
                new MemberRowMapper(), from, to
        );
        return results;
    }

    public Member selectById(Long id) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where id = ?",
                new MemberRowMapper(),
                id
        );

        return results.isEmpty() ? null : results.get(0);
    }
}
