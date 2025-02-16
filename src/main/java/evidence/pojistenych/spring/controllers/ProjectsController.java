package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.services.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

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
}
