package daelim.spring_ch10.service;

import daelim.spring_ch10.ListCommand;
import daelim.spring_ch10.Member;
import daelim.spring_ch10.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    public List<Member> selectByRegdate(ListCommand listCommand) {
        return memberDao.selectByRegdate(listCommand.getFrom(), listCommand.getTo());
    }

    public Member selectById(Long id) {
        return memberDao.selectById(id);
    }

    public List<Member> selectAll() {
        return memberDao.selectAll();
    }
}
