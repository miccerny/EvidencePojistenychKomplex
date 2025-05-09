package evidence.pojistenych.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * Zpracuje GET požadavek na adresu "/contact" a vrátí název šablony pro stránku kontaktu.
     * Tato metoda je zodpovědná za vykreslení stránky kontaktu ve webové aplikaci.
     *
     * @return Název šablony pro stránku kontaktu (v tomto případě "pages/home/contact")
     */
    @GetMapping("/contact")
    public String renderContact(){
        return "pages/home/contact";
    }



}
