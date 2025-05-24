package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.data.InsuranceType;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.repository.InsuranceRepository;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.CreateRecordFormData;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.PagedInsuranceDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.exceptions.InsuranceNotFoundException;
import evidence.pojistenych.spring.models.services.InsuranceService;
import evidence.pojistenych.spring.models.services.InsuredPersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller pro správu pojištění a pojištěných osob.
 * *
 * Obsahuje metody pro zobrazování, vytváření, úpravy a mazání pojistných záznamů.
 * Mapa základní URL je nastavená na root ("/").
 */
@Controller
@RequestMapping("/")
public class InsuranceController {
    // Service pro práci s pojištěnými osobami
    @Autowired
    private InsuredPersonService insuredPersonService;

    // Service pro práci s pojištěními
    @Autowired
    private InsuranceService insuranceService;

    // Mapper pro převod mezi DTO a entitami pojistných smluv
    @Autowired
    private InsuranceMapper insuranceMapper;

    // Repository pro přístup k datům pojištěných osob
    @Autowired
    private InsuredPersonRepository insuredPersonRepository;

    // Repository pro přístup k datům pojistných smluv
    @Autowired
    private InsuranceRepository insuranceRepository;


    /**
     * Metoda pro získání detailů pojištěné osoby podle jejího ID.
     * *
     * Načte informace o pojištěné osobě a jejích pojistkách, které se zobrazí
     * na stránce s detaily. Data se načítají stránkovaně (paging), takže se
     * dá zobrazit víc záznamů na víc stránek.
     *
     * @param id -  ID pojištěné osoby, kterou chceme zobrazit.
     * @param model -  Slouží k přidání dat, která chceme poslat do šablony (HTML stránky).
     * @param page  - Číslo stránky, kterou chceme zobrazit. Výchozí hodnota je 0.
     * @param size -  Počet záznamů na stránku. Výchozí hodnota je 3.
     * @return -  Název šablony, která se má zobrazit s informacemi o pojištěné osobě.
     */
    @GetMapping("insuredPersonDetail/{id}")
    public String getInsuredPersonDetail(@PathVariable Long id, Model model,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "3") int size) {

        PagedInsuranceDTO pagedInsuranceDTO = insuranceService.getInsuredPersonDetail(id, page, size);
        model.addAttribute("insuredPerson", pagedInsuranceDTO.getInsuredPerson());
        model.addAttribute("insurances", pagedInsuranceDTO.getInsurances());
        model.addAttribute("currentPage", pagedInsuranceDTO.getCurrentPage());
        model.addAttribute("totalPages", pagedInsuranceDTO.getTotalPages());
        model.addAttribute("totalItems", pagedInsuranceDTO.getTotalItems());

        return "pages/home/insuredPersonDetail";
    }

    /**
     * Metoda slouží pro zobrazení formuláře, kde se vytváří nová pojistná smlouva.
     * *
     * Tato metoda je dostupná jen pro uživatele s rolí ADMIN. Načte potřebná data
     * pro formulář, jako je informace o pojištěné osobě a typy pojištění.
     *
     * @param insuredPersonId - ID pojištěné osoby, pro kterou se formulář připravuje.
     * @param model - Objekt, do kterého přidáváme data pro šablonu (HTML stránku).
     * @return - Název šablony, která se má zobrazit pro vytvoření nové pojistky.
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("createRecord/{insuredPersonId}")
    public String renderCreateRecord(@PathVariable Long insuredPersonId,
                                     Model model){

        CreateRecordFormData formData = insuranceService.prepareCreateRecordForm(insuredPersonId);

        model.addAttribute("insuredPerson", formData.getInsuredPerson());
        model.addAttribute("insuranceRecordDTO", formData.getInsuranceRecordDTO());
        model.addAttribute("insuranceOptions", InsuranceType.values());
        return "pages/home/createRecord";
    }

    /**
     * Metoda zpracuje odeslaný formulář pro vytvoření nové pojistné smlouvy.
     * *
     * Tuto metodu může volat pouze uživatel s rolí ADMIN. Nejprve se zkontroluje,
     * jestli zadaný pojištěnec existuje. Poté se validují data z formuláře.
     * Pokud se najdou chyby, formulář se znovu zobrazí s chybovými hláškami.
     * Pokud je vše v pořádku, metoda vytvoří nový záznam o pojištění.
     *
     * @param insuredPersonId ID pojištěné osoby, ke které se pojistka vytváří.
     * @param insuranceRecord Data z formuláře, která obsahují informace o pojištění.
     * @param result Obsahuje případné chyby z validace formuláře.
     * @param redirectAttributes Slouží k přenesení zpráv (např. úspěch/neúspěch) po přesměrování.
     * @param model Objekt pro předání dat do šablony, pokud se formulář znovu zobrazí.
     * @return Přesměrování na detail pojištěnce, nebo zpět na formulář v případě chyby.
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/createRecord/{insuredPersonId}")
    public String createRecord(@PathVariable Long insuredPersonId,
            @Valid @ModelAttribute InsuranceRecordDTO insuranceRecord,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {
        InsuredPersonEntity insuredPerson = insuredPersonRepository.findById(insuredPersonId)
                .orElseThrow(() -> new RuntimeException("Pojištěnec nenalezen!"));

        model.addAttribute("insuredPerson", insuredPerson);
        model.addAttribute("insuranceOptions", InsuranceType.values());

        if(result.hasErrors()){
            model.addAttribute("insuranceRecordDTO", insuranceRecord);
            return "pages/home/createRecord";
        }

        try{
           insuranceService.createInsuranceRecord(insuranceRecord, insuredPersonId, result);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Chyba při vytváření pojištěnní");
            return "redirect:/home/createRecord/" + insuredPersonId;
        }

        redirectAttributes.addFlashAttribute("success", "Pojištění vytvořeno");
        return "redirect:/insuredPersonDetail/" + insuredPersonId;

    }

    /**
     * Metoda zobrazí formulář pro úpravu existující pojistné smlouvy.
     * *
     * Na základě ID pojištění načte data o pojistné smlouvě a také informace
     * o pojištěné osobě, aby bylo možné vše zobrazit ve formuláři.
     *
     * @param insuranceId - ID pojistné smlouvy, kterou chceme upravit.
     * @param model -  Slouží k předání dat do šablony (HTML stránky).
     * @return -  Název šablony pro editaci pojistné smlouvy.
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("/editRecord/{insuranceId}")
    public String renderEditForm(@PathVariable Long insuranceId,
                                 Model model){

        InsuranceRecordDTO fetchedInsurance = insuranceService.getById(insuranceId);

        Long insuredPersonId = fetchedInsurance.getInsuredPersonId();
        InsuredPersonDTO insuredPersonDTO = insuredPersonService.getById(insuredPersonId);

        model.addAttribute("insuredPerson", insuredPersonDTO);
        model.addAttribute("insuranceRecordDTO", fetchedInsurance);

        return "pages/home/editRecord";
    }

    /**
     * Metoda zpracuje odeslaný formulář pro úpravu pojistné smlouvy.
     * *
     * Pokud jsou ve formuláři chyby, vrátí se zpět do editačního formuláře.
     * Jinak aktualizuje data pojistky a přesměruje na detail pojištěné osoby.
     *
     * @param insuranceId -  ID pojistné smlouvy, kterou chceme upravit.
     * @param insuranceRecordDTO -  Data z formuláře s upravenými informacemi o pojistce.
     * @param result -  Výsledek validace dat z formuláře.
     * @param redirectAttributes -  Slouží k předání zpráv po přesměrování (např. úspěch).
     * @param model -  Slouží k předání dat do šablony v případě chyby.
     * @return -  Přesměrování na detail pojištěné osoby, nebo zobrazení editačního formuláře při chybě.
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("editRecord/{insuranceId}")
    public String editInsurance(@PathVariable long insuranceId,
                                @Valid InsuranceRecordDTO insuranceRecordDTO,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                Model model){
        if(result.hasErrors())
            return  renderEditForm(insuranceId, model);

        insuranceRecordDTO.setInsuranceId(insuranceId);
        insuranceService.edit(insuranceRecordDTO);
        redirectAttributes.addFlashAttribute("success", "Pojištění upraveno");

        return "redirect:/insuredPersonDetail/" + insuranceRecordDTO.getInsuredPersonId();
    }

    /**
     * Metoda smaže pojistnou smlouvu podle zadaného ID.
     * *
     * Tuto akci může provést pouze uživatel s rolí ADMIN. Po úspěšném smazání
     * se zobrazí potvrzovací zpráva a dojde k přesměrování na seznam všech pojistek.
     *
     * @param insuranceId - ID pojistné smlouvy, kterou chceme smazat.
     * @param redirectAttributes - Slouží k přidání zprávy, která se zobrazí po přesměrování.
     * @return -  Přesměrování na stránku se seznamem pojistných smluv.
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{insuranceId}")
    public  String deleteInsurance(@PathVariable long insuranceId,
                                   RedirectAttributes redirectAttributes){
        insuranceService.remove(insuranceId);
        redirectAttributes.addFlashAttribute("success", "Pojištění smazáno");

        return "redirect:/recordsOfInsuredPeople";
    }

    /**
     * Metoda zpracuje výjimku, která nastane, když se nenajde požadované pojištění.
     * *
     * Přidá do přesměrování chybovou zprávu a přesměruje uživatele na seznam pojištěnců.
     *
     * @param redirectAttributes - Slouží k přidání chybové zprávy, která se zobrazí po přesměrování.
     * @return - Přesměrování na stránku se seznamem pojištěnců.
     */
    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuranceNotFoundException(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen.");

        return "redirect:/recordsOfInsuredPeople";
    }
}
