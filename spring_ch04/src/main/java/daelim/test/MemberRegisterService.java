package daelim.test;

import daelim.spring_04.DuplicationMemberException;
import daelim.spring_04.Member;
import daelim.spring_04.MemberDao;
import daelim.spring_04.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collection;

@Component("regService")
public class MemberRegisterService {

    // private MemberDao memberDao = new MemberDao();

    @Autowired
    private MemberDao memberDao;

    // 생성자를 통해 의존 객체를 주입받음
    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
    public void regist(RegisterRequest req)  {
        // 1. 이메일로 회원 데이터 조회
        Member member = memberDao.selectByEmail(req.getEmail());
        // 2. member != null : 이미 이메을을 가진 회원이 존재
        // Exception 발생
        if(member != null) {
            throw new DuplicationMemberException("Duplication Email : "+ req.getEmail());
        }

        // 3. 신규 회원 등록
        memberDao.insert(new Member(req.getEmail(), req.getPassword(), req.getName(),
                LocalDateTime.now()
                                    )
        );
    }

    public void selectAll() {
        Collection<Member> memebers = memberDao.selectAll();
        for(Member member : memebers) {
            System.out.println(member.getId()+" : "+ member.getName() + "(" + member.getEmail() + ")");
        }
    }
}
