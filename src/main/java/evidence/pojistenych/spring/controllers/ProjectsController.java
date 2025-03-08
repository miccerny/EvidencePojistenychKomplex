package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.UserDTO;
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

import java.util.List;
import java.util.StringTokenizer;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private InsuredPersonService insuredPersonService;



    /**
     *
     * @return
     */


    @GetMapping("/evidencePojistencu")
    public String renderPojistenci(Model model){

        List<InsuranceRecordDTO> insuranceRecordDTO = insuranceService.getAll();

        if (insuranceRecordDTO.isEmpty()) {
            model.addAttribute("noInsurance", "Žádné pojištění k dispozici");  // Předáme informaci do modelu
        } else {
            model.addAttribute("insuranceRecordDTO", insuranceRecordDTO);
        }

        return "redirect:/projects/evidencePojistencu";
    }

    /**
     *
     * @param
     * @return
     */

    @GetMapping("createInsuredPerson")
    public String renderCreateInsured(@ModelAttribute InsuredPersonDTO insuredPersonDTO){

        if(insuredPersonDTO.getUserDTO() == null)
            insuredPersonDTO.setUserDTO(new UserDTO());

        return "pages/home/projects/createInsuredPerson";
    }

    @PostMapping("/createInsuredPerson")
    public String createInsuredPerson(@Valid @ModelAttribute InsuredPersonDTO insuredPersonDTO,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            System.out.println("❌ Formulář obsahuje chyby: " + bindingResult.getAllErrors());
            return "pages/home/projects/createInsuredPerson";
        }

        try {
            insuredPersonService.create(insuredPersonDTO);
            redirectAttributes.addFlashAttribute("success", "Pojištěnec uspěšně vytvořen");
        }catch (Exception e){
            System.out.println("❌ Chyba při ukládání do databáze: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Chyba při vytváření pojištěnce");

        }

        return "redirect:/projects/createInsuredPerson";
    }



    @Secured("ROLE_ADMIN")
    @GetMapping("/createRecord")
    public String renderCreateRecord(@ModelAttribute InsuranceRecordDTO insuranceRecordDTO){


        return "pages/home/projects/createRecord";
    }

    /**
     *
     * @param insuranceRecord
     * @param result
     * @return
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("createRecord")
    public String createRecord(@Valid @ModelAttribute InsuranceRecordDTO insuranceRecord,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors())
            return renderCreateRecord(insuranceRecord);

            insuranceService.create(insuranceRecord);
            redirectAttributes.addFlashAttribute("success", "Pojištění vytvořeno");

            return "redirect:/account/register";

    }






    @Secured("ROLE_ADMIN")
    @GetMapping("/editRecord/{insuranceId}")
    public String renderEditForm(@PathVariable long insuranceId,
                                 InsuranceRecordDTO insuranceRecordDTO){
        InsuranceRecordDTO fetchedInsurance = insuranceService.getById(insuranceId);
        insuranceMapper.updateInsuranceRecordDTO(fetchedInsurance, insuranceRecordDTO);

        return "pages/home/projects/editRecord";
    }


    @Secured("ROLE_ADMIN")
    @PostMapping("editRecord/{insuranceId}")
    public String editInsurance(@PathVariable long insuranceId,
                                @Valid InsuranceRecordDTO insuranceRecordDTO,
                                BindingResult result,
                                RedirectAttributes redirectAttributes){
        if(result.hasErrors())
            return  renderEditForm(insuranceId, insuranceRecordDTO);

        insuranceRecordDTO.setInsuranceId(insuranceId);
        insuranceService.edit(insuranceRecordDTO);
        redirectAttributes.addFlashAttribute("success", "Pojištění upraveno");

        return "redirect:/projects/evidencePojistencu";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{insuranceId}")
    public  String deleteInsurance(@PathVariable long insuranceId,
                                   RedirectAttributes redirectAttributes){
        insuranceService.remove(insuranceId);
        redirectAttributes.addFlashAttribute("success", "Článek smazán");

        return "redirect:/projects/evidencePojistencu";
    }

    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuranceNotFoundException(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("error", "Článek nenalezen.");
        return "redirect:/projects/evidencePojistencu";
    }


}
