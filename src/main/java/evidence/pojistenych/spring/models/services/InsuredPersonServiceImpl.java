package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class InsuredPersonServiceImpl {

    @Autowired
    private InsuredPersonRepository insuredPersonRepository;

    @Autowired
    InsuranceMapper insuranceMapper;


}
