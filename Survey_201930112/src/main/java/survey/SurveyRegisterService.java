package survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public class SurveyRegisterService {


    private SurveyDao surveyDao;

    public SurveyRegisterService(SurveyDao surveyDao) {
        if (surveyDao == null) {
            throw new IllegalArgumentException("memberDao must not be null");
        }
        this.surveyDao = surveyDao;
    }

    public void register(Survey survey) {
        // 신규 회원 등록
        surveyDao.insert(survey);
    }

}
