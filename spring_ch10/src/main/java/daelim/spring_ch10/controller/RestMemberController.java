package daelim.spring_ch10.controller;


import daelim.spring_ch10.DuplicationMemberException;
import daelim.spring_ch10.Member;
import daelim.spring_ch10.RegisterRequest;
import daelim.spring_ch10.service.MemberRegisterService;
import daelim.spring_ch10.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URL;
import java.util.List;

@RestController
public class RestMemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRegisterService memberRegisterService;

    @GetMapping("/api/members")
    public List<Member> members() {
        return memberService.selectAll();
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<Object> member(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Member member = memberService.selectById(id);
        if(member == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return null;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest registerRequest, Errors errors) {
        try {

            if(errors.hasErrors()) {
                String errorCodes = errors.getAllErrors().stream().map(error -> error.getCodes()[0]).collect(Collectors.joining(","));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = "+errorCodes));
            }
            Long newMemberId = memberRegisterService.regist(registerRequest);
//            response.setHeader("Location", String.format("/api/members/%d", newMemberId));
//            response.setStatus(HttpServletResponse.SC_CREATED);

            URI uri = URI.create("/api/members/" + newMemberId);
            return ResponseEntity.created(uri).build();

        } catch (DuplicationMemberException e) {
//            response.sendError(HttpServletResponse.SC_CONFLICT);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}

