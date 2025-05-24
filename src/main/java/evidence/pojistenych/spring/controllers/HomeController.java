package evidence.pojistenych.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller pro hlavní stránku aplikace.
 * *
 * Obsahuje metody, které obsluhují požadavky na domovskou stránku.
 * URL základní cesta je nastavena na "/home".
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    /**
     * Metoda zobrazí kontaktní stránku.
     *
     * @return Název šablony kontaktní stránky.
     */
    @GetMapping("/contact")
    public String renderContact(){
        return "pages/home/contact";
    }



}
