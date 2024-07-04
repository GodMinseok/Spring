package daelim.spring_ch10;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"daelim.spring_ch10.controller"})
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleRuntimeException(Exception e) {
        e.printStackTrace();
        return "error/commonException";
    }
}
