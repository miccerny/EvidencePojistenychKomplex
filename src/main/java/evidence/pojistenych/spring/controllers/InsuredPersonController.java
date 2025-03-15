package evidence.pojistenych.spring.controllers;


import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.exceptions.DuplicateEmailException;
import evidence.pojistenych.spring.models.services.InsuredPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Kontroler pro vyhledání a předání pojištěnců View stráně
 */
@Controller
@RequestMapping("/home")
public class InsuredPersonController {

    @Autowired
    private InsuredPersonService insuredPersonService;

    /**
     * Kontroler s metodou GET pro zobrazení všech pojištěnců v rámci vyhledání všech pojištěnců
     * @param model
     * @return - vrací HTML se zobrazenými pojištěnci
     */
    @GetMapping("/recordsOfInsuredPeople")
    public String renderPojistenci(Model model){

        List<InsuredPersonDTO> insuredPersonDTO = insuredPersonService.getAll();
        model.addAttribute("insuredPersonDTO", insuredPersonDTO);
        if (insuredPersonService.getAll().isEmpty())
            model.addAttribute("noInsuredPerson", "Žádný pojištěnec k dispozici");


        return "pages/home/recordsOfInsuredPeople";
    }

    /**
     * Metoda GET pro získání(zobrazení) formuláře pro vytvoření nového pojištěnce
     * @param insuredPersonDTO
     * @return - vrací vzor HTML formuláře pro vytvoření pojištěnce
     */
    @GetMapping("createInsuredPerson")
    public String renderCreateInsured(@ModelAttribute InsuredPersonDTO insuredPersonDTO){


        return "pages/home/createInsuredPerson";
    }

    /**
     * Metoda POST pro odeslání parametrů pojištěnce do databáze (v rámci MVC)
     * @param insuredPersonDTO
     * @param bindingResult
     * @param redirectAttributes - zobrazí hlášku s vytvořením pojištěného v rámci HTML
     * @return - přesměruuje (refreshne) HTML vzor s vytvotřením pojištěn=ho
     */
    @PostMapping("/createInsuredPerson")
    public String createInsuredPerson(@Valid @ModelAttribute InsuredPersonDTO insuredPersonDTO,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            System.out.println("Formulář obsahuje chyby: " + bindingResult.getAllErrors());
            return "pages/home/createInsuredPerson";
        }

        try {
            insuredPersonService.create(insuredPersonDTO);
            redirectAttributes.addFlashAttribute("success", "Pojištěnec uspěšně vytvořen");
        }catch (Exception e){
            System.out.println("❌ Chyba při ukládání do databáze: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Chyba při vytváření pojištěnce");
            bindingResult.rejectValue("email", "error", "Email je již registrován");
            return "pages/home/createInsuredPerson";
        }

        System.out.println("Ukládám pojištěného s emailem: " + insuredPersonDTO.getEmail());

        redirectAttributes.addFlashAttribute("success", "Pojištěnec vytvořen.");

        return "redirect:/home/createInsuredPerson";
    }

    /**
     * Metoda GET pro získání parametrů konkrétního pojištěnce z Modelu(databáze)
     * @param id
     * @param model
     * @return - vrátí HTML formulář s daty konkrétního pojištěnce
     */
    @GetMapping("/editInsuredPeople/{id}")
    public String editInsuredPerson(@PathVariable Long id, Model model) {
        InsuredPersonDTO insuredPersonDTO = insuredPersonService.getById(id);
        model.addAttribute("insuredPerson", insuredPersonDTO);
        return "pages/home/editInsuredPeople";
    }

    /**
     * Metoda POST pro odeslání upravených parametrů konkrétního pojištěnce do Modelu(databáze)
     * @param id
     * @param insuredPersonDTO
     * @param redirectAttributes
     * @return - vrátí HTML vzor formuláře o se všemi pojištěnci z databáze
     */
    @PostMapping("/editInsuredPeople/{id}")
    public String updateInsuredPerson(@ModelAttribute @PathVariable Long id,
                                      InsuredPersonDTO insuredPersonDTO,
                                      RedirectAttributes redirectAttributes) {

        insuredPersonDTO.setId(id);
        insuredPersonService.edit(insuredPersonDTO);

        redirectAttributes.addFlashAttribute("success", "Pojištěnec upraven.");
        return "redirect:/projects/recordsOfInsuredPeople";
    }

    /**
     * Metoda GET vyjímečně pro vymazání konrktétního pojištěnce (neučili jsme se DELETE) z Modelu(databáze)
     * @param id
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/deleteInsured/{id}")
    public String deleteInsuredPerson(@PathVariable Long id, RedirectAttributes redirectAttributes){

        System.out.println("Mazání pojištěného s ID: " + id);
        insuredPersonService.delete(id);
        System.out.println("Pojištěný smazán, přesměrování na seznam.");

        redirectAttributes.addFlashAttribute("success", "Pojištěnec smazán");
        return "redirect:/home/recordsOfInsuredPeople";
    }


    /**
     *
     * @param e
     * @param redirectAttributes
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleInsuredPersonNotFoundException(Exception e, RedirectAttributes redirectAttributes){

        e.printStackTrace();
        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen.");
        return "redirect:/home/recordsOfInsuredPeople";
    }


}
