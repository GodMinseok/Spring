package config;

import controller.SurveyController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import survey.SurveyDao;
import survey.SurveyRegisterService;

@Configuration
public class ControllerConfig {


    @Bean
    public SurveyDao surveyDao(JdbcTemplate jdbcTemplate) {
        return new SurveyDao(jdbcTemplate);
    }

    @Bean
    public SurveyRegisterService surveyRegisterService(SurveyDao surveyDao) {
        return new SurveyRegisterService(surveyDao);
    }
}
