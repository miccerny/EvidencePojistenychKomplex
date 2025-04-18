package evidence.pojistenych.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * metoda GET k získání dat pro formulář ke konraktování
     * @return - vrací HTML formulář ke kontaktování
     */
    @GetMapping("/contact")
    public String renderContact(){
        return "pages/home/contact";
    }



}
