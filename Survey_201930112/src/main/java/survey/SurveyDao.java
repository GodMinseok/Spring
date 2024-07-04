package survey;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SurveyDao {

    private JdbcTemplate jdbcTemplate;

    public SurveyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void insert(Survey survey) {
        String sql = "INSERT INTO SURVEY (Q1, Q2, Q3, RespondentName, RespondentAge, REGDATE) VALUES (?, ?, ?, ?, ?, NOW())";
        jdbcTemplate.update(sql, survey.getQ1(), survey.getQ2(), survey.getQ3(), survey.getRespondentName(), survey.getRespondentAge());
    }



    public List<Survey> selectAll() {

        String sql = "SELECT * FROM SURVEY";
        return jdbcTemplate.query(sql, new RowMapper<Survey>() {
            public Survey mapRow(ResultSet rs, int rowNum) throws SQLException {
                Survey survey = new Survey();
                survey.setId(rs.getInt("ID"));
                survey.setQ1(rs.getString("Q1"));
                survey.setQ2(rs.getString("Q2"));
                survey.setQ3(rs.getString("Q3"));
                survey.setRespondentName(rs.getString("RespondentName"));
                survey.setRespondentAge(rs.getInt("RespondentAge"));
                survey.setRegDate(rs.getTimestamp("REGDATE"));
                return survey;
            }
        });
    }

}