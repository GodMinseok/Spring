package spring_ch07;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;
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
    public Member selectByEmail(String email) {
        List<Member> results = jdbcTemplate.query(
                "select * from MEMBER where EMAIL = ?",
                new MemberRowMapper(),
                email
        );

        return results.isEmpty() ? null : results.get(0);
    }
    private static long nextId = 0;
    private Map<String, Member> map = new HashMap<>();

    public void insert(Member member) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
//                member.setId(++nextId);
//        map.put(member.getEmail(), member);
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
        Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class
        );
        return count;
    }
}
