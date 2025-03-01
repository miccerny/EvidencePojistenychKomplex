package evidence.pojistenych.spring.controllers;


import evidence.pojistenych.spring.models.dto.UserDTO;
import jakarta.validation.Valid;
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

    @GetMapping("login")
    public String renderLogin(){
        return "pages/account/login.html";
    }

    @GetMapping("register")
    public String renderRegister(@ModelAttribute UserDTO userDTO){
        return "pages/account/register";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute UserDTO userDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors())
            return renderRegister(userDTO);


        redirectAttributes.addFlashAttribute("success", "Uživatel registrován.");
        return "redirect:/account/login";
    }

}
