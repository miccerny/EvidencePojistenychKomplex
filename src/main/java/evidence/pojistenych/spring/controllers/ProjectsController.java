package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("createRecord")
    public String createRecord(@Valid @ModelAttribute InsuranceRecordDTO insuranceRecord,
                               BindingResult result) {
        if (result.hasErrors())
            return renderCreateRecord(insuranceRecord);

            insuranceService.create(insuranceRecord);

            return "redirect:/createRecord";

    }



    @GetMapping("/editRecord/{insuranceId}")
    public String renderEditForm(@PathVariable long insuranceId,
                                 InsuranceRecordDTO insuranceRecordDTO){
        InsuranceRecordDTO fetchedInsurance = insuranceService.getById(insuranceId);
        insuranceMapper.updateInsuranceRecordDTO(fetchedInsurance, insuranceRecordDTO);

        return "pages/home/projects/editRecord";
    }

    @PostMapping("editRecord/{insuranceId}")
    public String editInsurance(@PathVariable long insuranceId,
                                @Valid InsuranceRecordDTO insuranceRecordDTO,
                                BindingResult result){
        if(result.hasErrors())
            return  renderEditForm(insuranceId, insuranceRecordDTO);

        insuranceRecordDTO.setInsuranceId(insuranceId);
        insuranceService.edit(insuranceRecordDTO);

        return "redirect:/evidencePojistencu";
    }

}
