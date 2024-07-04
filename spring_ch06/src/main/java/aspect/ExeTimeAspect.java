package aspect;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Aspect
@Order(1)
public class ExeTimeAspect {

    // Pointcut : 공통 기능을 적용할 대상 설정
    // chapter07 패키지와 그 하위 패키지에 public 메서드
    @Pointcut("execution(public * daelim.spring_ch06..*(..))")
    private void publicTarget() {
    }

    // Advice : Around Advice
    // publicTarget() 메서드에 정의한 Pointcut에 공통기능을 적용한다.
    @Around("publicTarget()")
    public Object main(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            return result;
        } finally {
            long finish = System.nanoTime();

            // 호출되는 메서드에 대한 정보
            Signature sig = joinPoint.getSignature();

            // Object getTarget() : 대상객체를 구한다.
            // Object[] getArgs() : 파라미터 목록을 구한다.
            // sig.getName() : 호출되는 메서드의 이름을 구한다.
            System.out.printf("%s.%s(%s) 실행 시간 : %d ns\n",
                    joinPoint.getTarget().getClass().getSimpleName(),
                    sig.getName(),
                    Arrays.toString(joinPoint.getArgs()),
                    (finish - start));
        }
    }
}
