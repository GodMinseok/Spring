package daelim.spring_ch10.controller;

import daelim.spring_ch10.ListCommand;
import daelim.spring_ch10.Member;
import daelim.spring_ch10.MemberNotFoundException;
import daelim.spring_ch10.service.MemberService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {


    @Autowired
    private MemberService memberService;

    @RequestMapping("/members")
    public String list(Model model, @ModelAttribute("cmd") ListCommand command) {
        if(command.getFrom() != null && command.getTo() != null) {
            List<Member> members = memberService.selectByRegdate(command);
            model.addAttribute("members", members);
        }
        return "member/memberList";
    }

    @GetMapping("/members/{id}") /* {} 중요 언급 */
    public String detail(@PathVariable Long id, Model model) {
        Member member = memberService.selectById(id);
        if(member == null) {
            throw new MemberNotFoundException();
        }
        model.addAttribute("member", member);
        return "member/memberDetail";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException() {
        return "member/invalidId";
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public String handleNotFoundException() {
        return "member/noMember";
    }


}
