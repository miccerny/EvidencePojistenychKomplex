package evidence.pojistenych.spring.controllers;


import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.services.InsuredPersonService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/")
public class InsuredPersonController {

    @Autowired
    private InsuredPersonService insuredPersonService;

    /**
     * Kontroler s metodou GET pro zobrazení všech pojištěnců v rámci vyhledání všech pojištěnců
     * @param model
     * @return - vrací HTML se zobrazenými pojištěnci
     */
    @GetMapping("/")
    public String renderPojistenci(Model model){

        List<InsuredPersonDTO> insuredPersonDTO = insuredPersonService.getAll();
        model.addAttribute("insuredPersonDTO", insuredPersonDTO);

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
            return "pages/home/createInsuredPerson";
        }

        try {
            insuredPersonService.create(insuredPersonDTO);
            redirectAttributes.addFlashAttribute("success", "Pojištěnec uspěšně vytvořen");
        } catch (DataIntegrityViolationException ex) {
            bindingResult.rejectValue("email", "error", "Email je již registrován");
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Neočekávaná chyba při vytváření pojištěnce.");
        }
        System.out.println("Ukládám pojištěného s emailem: " + insuredPersonDTO.getEmail());

        redirectAttributes.addFlashAttribute("success", "Pojištěnec vytvořen.");

        return "redirect:/createInsuredPerson";
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
    public String updateInsuredPerson( @PathVariable Long id,
                                       @ModelAttribute InsuredPersonDTO insuredPersonDTO,
                                      RedirectAttributes redirectAttributes) {

        try{
            insuredPersonDTO.setId(id);
            insuredPersonService.edit(insuredPersonDTO);
            redirectAttributes.addFlashAttribute("success", "Pojištěnec upraven.");
        }catch (EntityNotFoundException e){
            redirectAttributes.addFlashAttribute("error", "Pojištěnec s ID " + id + " nebyl nalezen.");
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Chyba při úpravě pojištěnce.");
        }

        redirectAttributes.addFlashAttribute("success", "Pojištěnec upraven.");
        return "redirect:/";
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
        return "redirect:/recordsOfInsuredPeople";
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
        return "redirect:/recordsOfInsuredPeople";
    }


}
