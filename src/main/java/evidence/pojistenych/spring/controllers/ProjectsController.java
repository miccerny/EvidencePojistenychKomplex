package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/projects")
public class ProjectsController {

    @GetMapping("/evidencePojistencu")
    public String renderPojistenci(){
        return "pages/home/projects/evidencePojistencu";
    }

    @GetMapping("/createRecord")
    public String renderCreateRecord(@ModelAttribute InsuranceRecordDTO insuranceRecordDTO){
        return "pages/home/projects/createRecord";
    }

    @PostMapping("createRecord")
    public String createRecord(@Valid @ModelAttribute InsuranceRecordDTO insuranceRecord,
                               BindingResult result) {
        if (result.hasErrors())
            return renderCreateRecord(insuranceRecord);

            System.out.println(insuranceRecord.getPojisteni() + " - " + insuranceRecord.getPredmetPojisteni() +
                    " - " + insuranceRecord.getCastka() + " - " + insuranceRecord.getPlatnostOd() + " - " + insuranceRecord.getPlatnostDo());

            return "redirect:/createRecord";

    }
}
