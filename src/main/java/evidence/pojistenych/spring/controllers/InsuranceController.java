package evidence.pojistenych.spring.controllers;


import evidence.pojistenych.spring.data.InsuranceType;
import evidence.pojistenych.spring.data.entities.InsuranceEntity;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * Kontroler pro vyhledání a předání parametrů konkrétního pojištění View straně
 */

@Controller
@RequestMapping("/")
public class InsuranceController {

    private static final Logger log = LoggerFactory.getLogger(InsuranceController.class);
    @Autowired
    private InsuredPersonService insuredPersonService;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuredPersonRepository insuredPersonRepository;

    @Autowired
    private InsuranceRepository insuranceRepository;


    /**
     * Metoda GET pro získání parametrů všech pojištění konkrétního pojištěnce
     * @param id
     * @param model
     * @param page
     * @param size
     * @return - vrací vzor HTML se všemi pojištěními a konkrétní pojištěncem
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

    @Secured("ROLE_ADMIN")
    @GetMapping("createRecord/{insuredPersonId}")
    public String renderCreateRecord(@PathVariable Long insuredPersonId,
                                     Model model){

        CreateRecordFormData formData = insuranceService.prepareCreateRecordForm(insuredPersonId);

        model.addAttribute("insuredPerson", formData.getInsuredPerson());
        model.addAttribute("insuranceRecordDTO", formData.getInsuranceRecordDTO());
        return "pages/home/createRecord";
    }

    /**
     * metoda POST pro předáí nových parametrů pojištění modelu a service do databáze
     * @param insuredPersonId
     * @param insuranceRecord
     * @param result
     * @param redirectAttributes
     * @return - vrací HTML vzor se všemi pojištěními a novým pojištěním
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

        if(result.hasErrors()){
            model.addAttribute("insuranceOptions", InsuranceType.values());
            model.addAttribute("insuranceRecordDTO", insuranceRecord);
            System.out.println("Formulář obsahuje chyby: " + result.getAllErrors());
            return "pages/home/createRecord";
        }

        try{
            InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceRecord);
            insuranceEntity.setInsuredPerson(insuredPerson);
            insuranceRepository.save(insuranceEntity);


        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Chyba při vytváření pojištěnce");
            return "redirect:/home/createRecord/" + insuredPersonId;
        }

        redirectAttributes.addFlashAttribute("success", "Pojištění vytvořeno");
        return "redirect:/insuredPersonDetail/" + insuredPersonId;

    }

    /**
     * metoda GET pro získání parametrů existujícho pojištění z databáze k úpravě
     * @param insuranceId
     * @param model
     * @return - vrací HTML vzor přes Thymeleaf s konkrétními parametry k úpravě
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
     * metoda POST pro odeslání parametrů existujícho pojištění do databáze s upravenými parametry
     * @param insuranceId
     * @param insuranceRecordDTO
     * @param result
     * @param redirectAttributes
     * @param model
     * @return vrací HTML vzor se všmi pojištěními
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

        insuranceRecordDTO.setInsuranceId(insuranceId);

        insuranceService.edit(insuranceRecordDTO);
        redirectAttributes.addFlashAttribute("success", "Pojištění upraveno");

        return "redirect:/insuredPersonDetail/" + insuranceRecordDTO.getInsuredPersonId();
    }

    /**
     * metoda GET pro získání parametrů pojištěni a následnému odstranění
     * @param insuranceId
     * @param redirectAttributes
     * @return - vrací HTML vzor se všemi pojištěnci
     */
    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{insuranceId}")
    public  String deleteInsurance(@PathVariable long insuranceId,
                                   RedirectAttributes redirectAttributes){
        insuranceService.remove(insuranceId);
        redirectAttributes.addFlashAttribute("success", "Pojištění smazáno");

        return "redirect:/recordsOfInsuredPeople";
    }

    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuranceNotFoundException(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen.");

        return "redirect:/recordsOfInsuredPeople";
    }
}
