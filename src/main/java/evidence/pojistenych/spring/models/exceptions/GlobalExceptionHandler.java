package evidence.pojistenych.spring.models.exceptions;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Třída pro globální zpracování výjimek v aplikaci.
 * Zachytává DuplicateEmailException a vrací zpět formulář s chybovou hláškou.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Metoda pro zachycení výjimky DuplicateEmailException.
     * Přidá chybovou zprávu do modelu pod klíčem "emailError"
     * a vrátí název šablony pro zobrazení registračního formuláře.
     *
     * @param ex   výjimka DuplicateEmailException s chybovou zprávou
     * @param model model pro předání dat do šablony
     * @return název šablony registračního formuláře
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public String handleDuplicateEmail(DuplicateEmailException ex, Model model) {
        model.addAttribute("emailError", ex.getMessage());
        return "registration-form"; // název HTML šablony (např. Thymeleaf)
    }

    @ExceptionHandler(PasswordsDoNotEqualException.class)
    public String handlePasswordsDoNotEqual(PasswordsDoNotEqualException ex, Model model) {
        model.addAttribute("passwordError", ex.getMessage());
        model.addAttribute("confirmPasswordError", ex.getMessage());
        return "pages/account/register"; // návrat na formulář
    }
}
