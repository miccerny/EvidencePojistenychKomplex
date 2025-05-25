package evidence.pojistenych.spring.controllers;


import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.exceptions.DuplicateEmailException;
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
 * Controller pro správu pojištěných osob.
 * *
 * Obsahuje metody pro zobrazování, přidávání, úpravy a mazání pojištěných osob.
 * Základní URL adresa pro tento controller je nastavená na root ("/").
 */
@Controller
@RequestMapping("/")
public class InsuredPersonController {

    // Service pro práci s pojištěnými o
    @Autowired
    private InsuredPersonService insuredPersonService;

    /**
     * Metoda zobrazí seznam všech pojištěných osob.
     * *
     * Načte data o pojištěncích a předá je do šablony pro zobrazení.
     *
     * @param model -  Slouží k předání dat do šablony (HTML stránky).
     * @return - Název šablony se seznamem pojištěnců.
     */
    @GetMapping("/")
    public String renderPojistenci(Model model){

        List<InsuredPersonDTO> insuredPersonDTO = insuredPersonService.getAll();
        model.addAttribute("insuredPersonDTO", insuredPersonDTO);

        return "pages/home/recordsOfInsuredPeople";
    }

    /**
     * Metoda zobrazí formulář pro vytvoření nové pojištěné osoby.
     *
     * @param insuredPersonDTO - Objekt sloužící k vyplnění formuláře.
     * @return - Název šablony pro vytvoření nové pojištěné osoby.
     */
    @GetMapping("createInsuredPerson")
    public String renderCreateInsured(@ModelAttribute InsuredPersonDTO insuredPersonDTO){
        return "pages/home/createInsuredPerson";
    }

    /**
     * Metoda zpracuje formulář pro vytvoření nové pojištěné osoby.
     * *
     * Nejprve se ověří správnost dat ve formuláři. Pokud jsou chyby, formulář
     * se zobrazí znovu s chybovými hláškami. Pokusí se vytvořit novou pojištěnou osobu.
     * Pokud nastane chyba s duplicitním emailem, zobrazí se speciální hláška.
     * Při jiných chybách se zobrazí obecná chybová zpráva.
     *
     * @param insuredPersonDTO Data nové pojištěné osoby z formuláře.
     * @param bindingResult Výsledek validace formulářových dat.
     * @param redirectAttributes Slouží k přenesení zpráv po přesměrování (např. úspěch nebo chyba).
     * @return Návrat na formulář při chybě, nebo přesměrování zpět na formulář po úspěchu.
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
            return "redirect:/createInsuredPerson";
        } catch (DuplicateEmailException ex) {
            bindingResult.rejectValue("email", "error.email", ex.getMessage());
            return "pages/home/createInsuredPerson";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", "Neočekávaná chyba při vytváření pojištěnce.");
            return "redirect:/createInsuredPerson";
        }
    }

    /**
     * Metoda zobrazí formulář pro úpravu údajů pojištěné osoby podle jejího ID.
     *
     * Načte data pojištěné osoby a předá je do šablony pro editaci.
     *
     * @param id - ID pojištěné osoby, kterou chceme upravit.
     * @param model - Slouží k předání dat do šablony (HTML stránky).
     * @return - Název šablony pro úpravu pojištěné osoby.
     */
    @GetMapping("/editInsuredPeople/{id}")
    public String editInsuredPerson(@PathVariable Long id, Model model) {
        InsuredPersonDTO insuredPersonDTO = insuredPersonService.getById(id);
        model.addAttribute("insuredPerson", insuredPersonDTO);
        return "pages/home/editInsuredPeople";
    }

    /**
     * Metoda zpracuje úpravu údajů pojištěné osoby podle jejího ID.
     * *
     * Pokusí se upravit pojištěnce, pokud pojištěnec s daným ID neexistuje,
     * zobrazí se chybová zpráva. V případě jiných chyb se zobrazí obecná chyba.
     *
     * @param id - ID pojištěné osoby, kterou chceme upravit.
     * @param insuredPersonDTO - Nová data pojištěné osoby z formuláře.
     * @param redirectAttributes - Slouží k předání zpráv (úspěch, chyba) po přesměrování.
     * @return - Přesměrování na hlavní stránku.
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
     * Metoda smaže pojištěnou osobu podle jejího ID.
     * *
     * Po úspěšném smazání přesměruje na stránku se seznamem pojištěných osob
     * a zobrazí zprávu o úspěchu.
     *
     * @param id ID pojištěné osoby, kterou chceme smazat.
     * @param redirectAttributes Slouží k předání zprávy o úspěchu po přesměrování.
     * @return Přesměrování na seznam pojištěných osob.
     */
    @GetMapping("/deleteInsured/{id}")
    public String deleteInsuredPerson(@PathVariable Long id, RedirectAttributes redirectAttributes){

        insuredPersonService.delete(id);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec smazán");
        return "redirect:/recordsOfInsuredPeople";
    }

    /**
     * Metoda zachytí výjimku při nenalezení pojištěnce.
     * *
     * Vypíše chybu do konzole, přidá zprávu o chybě do RedirectAttributes
     * a přesměruje uživatele zpět na seznam pojištěných osob.
     *
     * @param e -  Výjimka, která byla vyhozena při nenalezení pojištěnce.
     * @param redirectAttributes - Slouží k předání chybové zprávy po přesměrování.
     * @return - Přesměrování na stránku se seznamem pojištěných osob.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleInsuredPersonNotFoundException(Exception e, RedirectAttributes redirectAttributes){

        e.printStackTrace();
        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen.");
        return "redirect:/recordsOfInsuredPeople";
    }


}
