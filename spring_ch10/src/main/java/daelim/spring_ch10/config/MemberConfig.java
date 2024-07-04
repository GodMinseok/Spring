package daelim.spring_ch10.config;

import daelim.spring_ch10.service.AuthService;
import daelim.spring_ch10.service.ChangePasswordService;
import daelim.spring_ch10.MemberDao;
import daelim.spring_ch10.service.MemberRegisterService;
import daelim.spring_ch10.service.MemberService;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class MemberConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        DataSource ds = new DataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://daelim-spring.cjcs8kueeky3.ap-northeast-2.rds.amazonaws.com/daelim?characterEncoding=utf8");
        ds.setUsername("admin");
        ds.setPassword("minseok0724");
        ds.setInitialSize(2);
//        ds.setMaxActive(10);
//        ds.setTestWhileIdle(true); // 유휴 커넥션 검사
//        ds.setTimeBetweenEvictionRunsMillis(1000 * 10); // 10초 주기로 유휴 커넥션이 유효한지 검사
//        ds.setMinEvictableIdleTimeMillis(1000 * 60 * 3); // 최소 유휴시간 3분
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    public MemberDao memberDao() {
        return new MemberDao(dataSource());
    }

    @Bean
    public MemberRegisterService memberRegisterService() {
        return new MemberRegisterService(memberDao());
    }

    @Bean
    public ChangePasswordService changePasswordService() {
        return new ChangePasswordService();
    }

    @Bean
    public AuthService authService(){ return new AuthService(); }


    @Bean
    public MemberService memberService() {
        return new MemberService();
    }

}