package evidence.pojistenych.spring.controllers;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.dto.mappers.InsuredPersonMapper;
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

@Controller
@RequestMapping("/projects")
public class InsuredPersonController {

    @Autowired
    private InsuredPersonService insuredPersonService;


    @GetMapping("/recordsOfInsuredPeople")
    public String renderPojistenci(Model model){

        List<InsuredPersonDTO> insuredPersonDTO = insuredPersonService.getAll();
        model.addAttribute("insuredPersonDTO", insuredPersonDTO);
        if (insuredPersonService.getAll().isEmpty())
            model.addAttribute("noInsuredPerson", "Žádný pojištěnec k dispozici");  // Předáme informaci do modelu


        return "pages/home/projects/recordsOfInsuredPeople";
    }


    @GetMapping("createInsuredPerson")
    public String renderCreateInsured(@ModelAttribute InsuredPersonDTO insuredPersonDTO){


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

        System.out.println("Ukládám pojištěného s emailem: " + insuredPersonDTO.getEmail());


        redirectAttributes.addFlashAttribute("success", "Pojištěnec vytvořen.");

        return "redirect:/projects/createInsuredPerson";
    }


    @GetMapping("/editInsuredPeople/{id}")
    public String editInsuredPerson(@PathVariable Long id, Model model) {
        InsuredPersonDTO insuredPersonDTO = insuredPersonService.getById(id);
        model.addAttribute("insuredPerson", insuredPersonDTO);
        return "pages/home/projects/editInsuredPeople"; // Odkazuje na `insuredPersonEdit.html`
    }

    @PostMapping("/editInsuredPeople/{id}")
    public String updateInsuredPerson(@ModelAttribute @PathVariable Long id,
                                      InsuredPersonDTO insuredPersonDTO,
                                      RedirectAttributes redirectAttributes) {

        insuredPersonDTO.setId(id);
        insuredPersonService.edit(insuredPersonDTO);

        redirectAttributes.addFlashAttribute("success", "Pojištěnec upraven.");
        return "redirect:/projects/recordsOfInsuredPeople";
    }

    @GetMapping("/deleteInsured/{id}")
    public String deleteInsuredPerson(@PathVariable Long id){
        System.out.println("Mazání pojištěného s ID: " + id);
       insuredPersonService.delete(id);
        System.out.println("Pojištěný smazán, přesměrování na seznam.");
     return "redirect:/projects/recordsOfInsuredPeople";
    }



    @ExceptionHandler({InsuranceNotFoundException.class})
    public String handleInsuranceNotFoundException(RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("error", "Pojištěnec nenalezen.");
        return "redirect:/projects/recordsOfInsuredPeople";
    }


}
