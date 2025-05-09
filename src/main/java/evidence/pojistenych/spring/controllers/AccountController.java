package evidence.pojistenych.spring.controllers;


import evidence.pojistenych.spring.models.dto.UserDTO;
import evidence.pojistenych.spring.models.exceptions.DuplicateEmailException;
import evidence.pojistenych.spring.models.exceptions.PasswordsDoNotEqualException;
import evidence.pojistenych.spring.models.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    /**
     * Zobrazí přihlašovací formulář uživatele.
     * Tato metoda vrací název šablony pro stránku s přihlášením.
     *
     * @return cesta k šabloně přihlašovací stránky
     */
    @GetMapping("login")
    public String renderLogin(){
        return "pages/account/login.html";
    }

    /**
     * Zobrazí registrační formulář pro vytvoření nového uživatele.
     * @param userDTO prázdný DTO objekt pro naplnění registračního formuláře
     * @return cesta k šabloně registrační stránky
     */
    @GetMapping("register")
    public String renderRegister(@ModelAttribute UserDTO userDTO){

        return "pages/account/register";
    }

    /**
     * Zpracuje POST požadavek pro registraci nového uživatele.
     * *
     * Provádí validaci vstupních dat pomocí anotace {@code @Valid} a dodatečných pravidel ve službě.
     * Pokud se vyskytnou chyby, zůstane na registrační stránce a zobrazí chybové zprávy.
     * Po úspěšné registraci přesměruje uživatele na přihlašovací stránku s úspěšnou hláškou.
     *
     * @param userDTO objekt s údaji z registračního formuláře
     * @param bindingResult obsahuje výsledky validace a případné chyby
     * @param redirectAttributes slouží k předání zprávy po přesměrování
     * @return název šablony nebo přesměrování podle výsledku registrace
     */
    @PostMapping("register")
    public String register(@Valid @ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors())
            return "pages/account/register";

        userService.create(userDTO, false, bindingResult);

        if(bindingResult.hasErrors())
            return "pages/account/register";

        redirectAttributes.addFlashAttribute("success", "Uživatel registrován.");
        return "redirect:/account/login";
    }

}
