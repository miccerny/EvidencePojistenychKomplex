package evidence.pojistenych.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String renderIndex(){
        return "pages/home/index";
    }

    @GetMapping("/contact")
    public String renderContact(){
        return "pages/home/contact";
    }


    @GetMapping("/dovednosti")
    public String renderSkills(){
        return "pages/home/dovednosti";
    }


}
