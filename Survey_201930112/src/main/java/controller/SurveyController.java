package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import survey.Survey;
import survey.SurveyDao;
import survey.SurveyRegisterService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SurveyController {

    @Autowired
    private SurveyRegisterService surveyRegisterService;
    @Autowired
    private SurveyDao surveyDao;

    @GetMapping("/surveyForm")
    public String handleSurvey(Model model) {
        System.out.println("startSurvey >>>>>>>");
        model.addAttribute("surveyCommand", new SurveyCommand());
        return "/survey/surveyForm";
    }

    @PostMapping("/surveyComplete")
    public String complete(@Valid @ModelAttribute("surveyCommand") SurveyCommand surveyCommand, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // 검증 오류가 있을 경우, 사용자에게 오류 메시지를 보여주며 폼으로 돌아갑니다.
            return "/survey/surveyForm";
        }

        Survey survey = new Survey();
        survey.setQ1(surveyCommand.getQ1());
        survey.setQ2(surveyCommand.getQ2());
        survey.setQ3(surveyCommand.getQ3());
        survey.setRespondentName(surveyCommand.getRespondentName());
        survey.setRespondentAge(surveyCommand.getRespondentAge());

        surveyRegisterService.register(survey);

        model.addAttribute("survey", survey);
        return "survey/surveyComplete";
    }

    @GetMapping("/surveyList")
    public String list(Model model) {
        List<Survey> surveys = surveyDao.selectAll();
        model.addAttribute("list", surveys);
        return "survey/surveyList";
    }

}
