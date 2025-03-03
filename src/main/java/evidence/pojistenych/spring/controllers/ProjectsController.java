package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.exceptions.InsuranceNotFoundException;
import evidence.pojistenych.spring.models.services.InsuranceService;
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

    /**
     *
     * @return
     */


    @GetMapping("/evidencePojistencu")
    public String renderPojistenci(Model model){

        List<InsuranceRecordDTO> insuranceRecordDTO = insuranceService.getAll();
        model.addAttribute("insuranceRecordDTO", insuranceRecordDTO);
        return "pages/home/projects/evidencePojistencu";
    }

    /**
     *
     * @param insuranceRecordDTO
     * @return
     */


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

            return "redirect:/projects/evidencePojistencu";

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
