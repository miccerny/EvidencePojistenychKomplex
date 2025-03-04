package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;

import java.util.Optional;

public interface InsuredPersonService {

    Optional<InsuredPersonDTO> getAll();

    void create(InsuredPersonDTO insuredPersonDTO);

    InsuredPersonDTO getById (long insuredPersonId);


}
