package spring_ch07.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_ch07.config.AppContext;
import spring_ch07.db.DbQuery;

public class MainForTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
        DbQuery dbQuery = ctx.getBean("dbQuery", DbQuery.class);
        System.out.println(dbQuery.count());
    }
}
