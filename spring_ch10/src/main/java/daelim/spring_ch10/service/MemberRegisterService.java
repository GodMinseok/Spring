package daelim.spring_ch10.service;

import daelim.spring_ch10.DuplicationMemberException;
import daelim.spring_ch10.Member;
import daelim.spring_ch10.MemberDao;
import daelim.spring_ch10.RegisterRequest;

import java.time.LocalDateTime;
import java.util.Collection;

public class MemberRegisterService {

    //private MemberDao memberDao = new MemberDao();

    private MemberDao memberDao;

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void regist(RegisterRequest req) {
        // 1 이메일로 회원 데이터 조회
        Member member = memberDao.selectByEmail(req.gerEmail());
        // 2 member != null : 이미 이메일을 가진 회원이 존재
        // Exception 발생
    if (member != null) {
        throw new DuplicationMemberException("Duplication Email : " + req.gerEmail());
    }
        // 3 신규 회원 등록
        memberDao.insert(new Member(req.gerEmail(), req.getPassword(), req.getName(), LocalDateTime.now()));
    }
    public void selectAll(){
        Collection<Member> members = memberDao.selectAll();
        for (Member member : members) {
            System.out.println(member.getId()+":"+member.getName()+"("+member.getEmail()+")");
        }
    }

}

