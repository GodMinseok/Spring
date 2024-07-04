package daelim.spring_04.config;

import daelim.spring_04.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {"daelim.spring_04", "daelim.test"})
public class AppContext {

    @Bean
    public MemberDao memberDao() {
        return new MemberDao();
    }

//    @Bean
//    public MemberRegisterService memberRegisterService() {
//        return new MemberRegisterService(memberDao());
//    }

//    @Bean
//    public ChangePasswordService changePasswordService() {
//        ChangePasswordService changePasswordService = new ChangePasswordService();
////        changePasswordService.setMemberDao(memberDao());
//        return changePasswordService;
//    }

    @Bean
    @Qualifier("memberPrinter")
    public MemberPrinter memberPrinter1() {
        return new MemberPrinter();
    }

    @Bean
    @Qualifier("summaryPrinter")
    public MemberSummaryPrinter memberPrinter2() {
        return new MemberSummaryPrinter();
    }

//    @Bean
//    public MemberListPrinter memberListPrinter() {
//        return new MemberListPrinter();
//    }

//    @Bean
//    public MemberInfoPrinter memberInfoPrinter() {
//        MemberInfoPrinter memberInfoPrinter = new MemberInfoPrinter();
////        memberInfoPrinter.setMemberDao(memberDao());
//        memberInfoPrinter.setMemberPrinter(memberPrinter2());
//        return memberInfoPrinter;
//    }



    @Bean
    public VersionPrinter versionPrinter() {
        VersionPrinter versionPrinter = new VersionPrinter();
        versionPrinter.setMajorVersion(2);
        versionPrinter.setMajorVersion(1);
        return versionPrinter;
    }

}
