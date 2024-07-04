package daelim.spring_ch03.main;

import daelim.spring_ch03.*;
import daelim.spring_ch03.config.AppConf1;
import daelim.spring_ch03.config.AppConf2;
import daelim.spring_ch03.config.AppContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForSpring {

    private static ApplicationContext ctx;
    private static Assembler assembler = new Assembler();

    public static void main(String[] args) throws IOException {

        // ctx = new AnnotationConfigApplicationContext(AppContext.class);
        ctx = new AnnotationConfigApplicationContext(AppConf1.class, AppConf2.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // MemberDao memberDao = new MemberDao();

        while(true) {
            System.out.println("명령어를 입력하세요.");
            String command = reader.readLine();
            if(command.equalsIgnoreCase("exit")) {
                System.out.println("종료합니다.");
                break;
            }

            // 회원등록
            if(command.startsWith("new")) {
                processNewCommand(command.split(" "));
                continue;
            }

            // 비밀번호 변경
            if(command.startsWith("change")) {
                processChangeCommand(command.split(" "));
                continue;
            }

            // 전체 회원 리스트 출력
            if(command.startsWith("list")) {
                processListCommand();
                continue;
            }

            // 회원 정보 출력
            if(command.startsWith("info")) {
                processInfoCommand(command.split(" "));
                continue;
            }

            // 버전 정보 출력
            if(command.startsWith("version")) {
                processVersionCommand();
                continue;
            }

            printHelp();
        }

    }

    private static void processNewCommand(String[] arg) {

        if(arg.length != 5) {
            printHelp();
            return;
        }

        // 1. 객체 생성 방식
        // MemberDao memberDao = new MemberDao();
        // MemberRegisterService memberRegisterService = new MemberRegisterService(memberDao);
        // 2. Assembler 객체를 통한 주입 방식
        // MemberRegisterService memberRegisterService = assembler.getMemberRegisterService();
        // 3. Spring Bean 을 통한 주입 방식
        MemberRegisterService memberRegisterService =
                ctx.getBean("memberRegisterService", MemberRegisterService.class);

        // 명령어 예제 : new test@test.com TEST asdf asdf
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(arg[1]);
        registerRequest.setName(arg[2]);
        registerRequest.setPassword(arg[3]);
        registerRequest.setConfirmPassword(arg[4]);

        if(!registerRequest.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호와 확인이 일치하지 않습니다.");
            return;
        }

        try {
            memberRegisterService.regist(registerRequest);
            System.out.println("회원등록이 되었습니다.");
            memberRegisterService.selectAll();
        } catch (DuplicationMemberException e) {
            System.out.println("이미 존재하는 이메일입니다.");
        }
    }

    private static void processChangeCommand(String[] arg) {
        if(arg.length != 4) {
            printHelp();
            return;
        }

//        ChangePasswordService changePasswordService = new ChangePasswordService();
//        changePasswordService.setMemberDao(memberDao);
//        ChangePasswordService changePasswordService = assembler.getChangePasswordService();
        ChangePasswordService changePasswordService = ctx.getBean("changePasswordService", ChangePasswordService.class);

        try {
            changePasswordService.changePassword(arg[1], arg[2], arg[3]);
        } catch (MemberNotFoundException e) {
            System.out.println("존재하지 않는 이메일입니다.");
        } catch (WrongPasswordException e) {
            System.out.println("암호가 일치하지 않습니다.");
        }

    }

    private static void printHelp() {
        System.out.println();
        System.out.println("잘못된 명령입니다. 아래 명령어 사용법을 확인하세요");
        System.out.println("new 이메일 이름 암호 암호확인");
        System.out.println("change 이메일 현재암호 변경암호");
        System.out.println();
    }

    private static void processListCommand() {
        MemberListPrinter memberListPrinter = ctx.getBean("memberListPrinter", MemberListPrinter.class);
        memberListPrinter.printAll();
    }

    private static void processInfoCommand(String[] arg) {
        // info 이메일
        if(arg.length != 2) {
            printHelp();
            return;
        }

        MemberInfoPrinter memberInfoPrinter = ctx.getBean("memberInfoPrinter", MemberInfoPrinter.class);
        memberInfoPrinter.printMember(arg[1]);
    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }
}