package daelim.spring_ch03;

public class Assembler {

    private MemberDao memberDao;
    private  MemberRegisterService memberRegisterService;
    private ChangePasswordService changePasswordService;

    public Assembler() {
        memberDao = new MemberDao();
        changePasswordService = new ChangePasswordService();
        changePasswordService.setMemberDao(memberDao);
        memberRegisterService = new MemberRegisterService(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService() {
        return memberRegisterService;
    }

    public ChangePasswordService getChangePasswordService() {
        return changePasswordService;
    }
}
