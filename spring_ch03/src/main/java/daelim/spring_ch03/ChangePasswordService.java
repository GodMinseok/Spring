package daelim.spring_ch03;

public class ChangePasswordService {

    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void changePassword(String email, String oldPassword, String newPassword) {
        // 1. 이메일로 회원 조회
        Member member = memberDao.selectByEmail(email);

        // 2. 존재하지 않는 회원 예외처리
        if(member == null) {
            throw new MemberNotFoundException();
        }

        // 3. 비밀번호 변경
        member.changePassword(oldPassword, newPassword);
        memberDao.update(member);
    }
}
