package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.repository.InsuranceRepository;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
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

import java.util.List;

@Controller
@RequestMapping("/projects")
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

    @GetMapping("insuredPersonDetail/{id}")
    public String getInsuredPersonDetail(@PathVariable Long id, Model model) {
        InsuredPersonEntity insuredPersonEntity = insuredPersonRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pojištěnec nenalezen"));


        List<InsuranceEntity> insuranceRecords = insuranceRepository.findByInsuredPerson(insuredPersonEntity);
        model.addAttribute("insuredPerson", insuredPersonEntity);
        model.addAttribute("insurances", insuranceRecords);

        return "pages/home/projects/insuredPersonDetail"; // Odkaz na šablonu Thymeleaf
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("createRecord/{insuredPersonId}")
    public String renderCreateRecord(@PathVariable Long insuredPersonId,
                                     Model model){

        InsuranceRecordDTO insuranceRecordDTO = new InsuranceRecordDTO();

        insuranceRecordDTO.setInsuredPersonId(insuredPersonId);

        InsuredPersonDTO insuredPerson = insuredPersonService.getById(insuredPersonId);

        model.addAttribute("insuredPerson", insuredPerson);
        model.addAttribute("insuranceRecordDTO", insuranceRecordDTO);
        return "pages/home/projects/createRecord";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/createRecord/{insuredPersonId}")
    public String createRecord(@PathVariable Long insuredPersonId,
            @Valid @ModelAttribute InsuranceRecordDTO insuranceRecord,
                               BindingResult result,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return  "pages/home/projects/insuredPersonDetail";

        insuranceRecord.setInsuredPersonId(insuredPersonId);
        insuranceService.create(insuranceRecord);
        redirectAttributes.addFlashAttribute("success", "Pojištění vytvořeno");

        return "redirect:/projects/insuredPersonDetail/" + insuranceRecord.getInsuredPersonId();

    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/editRecord/{isnuranceId}")
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

        return "redirect:/projects/recordsOfInsuredPeople";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("delete/{insuranceId}")
    public  String deleteInsurance(@PathVariable long insuranceId,
                                   RedirectAttributes redirectAttributes){
        insuranceService.remove(insuranceId);
        redirectAttributes.addFlashAttribute("success", "Pojištěnec smazán");

        return "redirect:/projects/recordsOfInsuredPeople";
    }
}
