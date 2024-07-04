package daelim.spring_04;

public class MemberSummaryPrinter extends MemberPrinter{
    @Override
    public void print(Member member) {
        System.out.printf("회원 정보 : 아이디 = %d, 이메일 = %s \n"
                ,member.getId(), member.getEmail());
    }
}
