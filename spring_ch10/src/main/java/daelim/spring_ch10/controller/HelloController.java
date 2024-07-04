package daelim.spring_ch10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/test")
public class HelloController {

//    @RequestMapping(value = "/hello", method = RequestMethod.POST)
//    @PostMapping("/hello")
    @GetMapping("/hello")
    public String hello(Model model, @RequestParam(value = "name", required = false) String name) {
        model.addAttribute("greeting", "안녕하세요~" + name);
        return "hello";
    }
}

