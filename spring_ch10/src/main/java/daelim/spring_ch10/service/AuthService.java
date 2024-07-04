package daelim.spring_ch10.service;

import daelim.spring_ch10.AuthInfo;
import daelim.spring_ch10.Member;
import daelim.spring_ch10.MemberDao;
import daelim.spring_ch10.WrongPasswordException;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthService {

    @Autowired
    private MemberDao memberDao;

    public AuthInfo authenicate(String email, String password) {
        Member member = memberDao.selectByEmail(email);
        if( member == null) throw new WrongPasswordException();
        if(!member.matchPassword(password)) throw new WrongPasswordException();
        return new AuthInfo(member.getId(), member.getEmail(), member.getName());
    }

}
