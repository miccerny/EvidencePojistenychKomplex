package evidence.pojistenych.spring.controllers;


import evidence.pojistenych.spring.models.dto.UserDTO;
import evidence.pojistenych.spring.models.exceptions.DuplicateEmailException;
import evidence.pojistenych.spring.models.exceptions.PasswordsDoNotEqualException;
import evidence.pojistenych.spring.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller pro správu uživatelských účtů.
 * *
 * Obsahuje metody pro přihlašování, registraci a správu uživatelských dat.
 * Základní URL adresa pro tento controller je "/account".
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    // Service pro správu uživatelů
    @Autowired
    private UserService userService;

    /**
     * Metoda zobrazí stránku pro přihlášení uživatele.
     *
     * @return - Název šablony stránky přihlášení.
     */
    @GetMapping("login")
    public String renderLogin(){
        return "pages/account/login.html";
    }

    /**
     * Zobrazí registrační formulář pro vytvoření nového uživatele.
     * @param userDTO -  prázdný DTO objekt pro naplnění registračního formuláře
     * @return -  cesta k šabloně registrační stránky
     */
    @GetMapping("register")
    public String renderRegister(@ModelAttribute UserDTO userDTO, Model model){
        return "pages/account/register";
    }

    /**
     * Metoda zpracuje registrační formulář pro nového uživatele.
     * *
     * Nejprve se ověří data z formuláře. Pokud jsou chyby, zobrazí se stránka
     * registrace znovu s chybovými hláškami. Pokud je vše správně, vytvoří se
     * nový uživatel a uživatel je přesměrován na přihlašovací stránku s potvrzením.
     *
     * @param userDTO -  Data uživatele z formuláře.
     * @param bindingResult -  Výsledek validace formulářových dat.
     * @param redirectAttributes -  Slouží k předání zpráv po přesměrování (např. úspěch).
     * @return -  Název šablony registrační stránky při chybě, nebo přesměrování na přihlášení po úspěchu.
     */
    @PostMapping("register")
    public String register(@Valid @ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors())
            return "pages/account/register";

        try {
            userService.create(userDTO, false);
            redirectAttributes.addFlashAttribute("success", "Uživatel registrován.");
        } catch (PasswordsDoNotEqualException e){
            bindingResult.rejectValue("confirmPassword", "error.confirmPassword", e.getMessage());
            return "pages/account/register";
        }catch (DuplicateEmailException e){
            bindingResult.rejectValue("email", "error.email", e.getMessage());
            return "pages/account/register";
        }

        return "redirect:/account/login";
    }

}
