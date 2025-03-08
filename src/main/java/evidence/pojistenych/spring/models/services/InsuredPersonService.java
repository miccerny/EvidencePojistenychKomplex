package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;

import java.util.Optional;

public interface InsuredPersonService {


    InsuredPersonEntity create(InsuredPersonDTO insuredPersonDTO);



}
