package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import java.util.List;


public interface InsuredPersonService {

    List<InsuredPersonDTO> getAll();

    void create(InsuredPersonDTO insuredPersonDTO);

     void edit(InsuredPersonDTO insuredPersonDTO);

    InsuredPersonDTO getById(Long id);

    void delete(Long id);



}
