package spring_ch07.db;

import com.mysql.cj.protocol.Resultset;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DbQuery {

    @Autowired
    private DataSource dataSource;

    public int count() {
        Connection conn = null;
        try {

            conn = dataSource.getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from MEMBER");
            rs.next();

            conn.commit(); // 트랜잭션 종료
            return rs.getInt(1);

        } catch (SQLException e) {
            if(conn != null) {
                // 트랜잭션 범위 종료 : 롤백
                try { conn.rollback(); }catch(SQLException ex) {}
            }
            throw new RuntimeException(e);
        } finally {
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {

                }
            }

        }
    }
}
