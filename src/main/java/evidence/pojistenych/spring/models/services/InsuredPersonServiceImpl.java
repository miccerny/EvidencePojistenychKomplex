package evidence.pojistenych.spring.models.services;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.data.repository.UserRepository;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class InsuredPersonServiceImpl implements InsuredPersonService {

    @Autowired
    private InsuredPersonRepository insuredPersonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    InsuranceMapper insuranceMapper;

    @Override
    public void create(InsuredPersonDTO insuredPersonDTO) {
        // Vytvoření nové entity pojištěnce
        InsuredPersonEntity insuredPersonEntity = insuranceMapper.toEntity(insuredPersonDTO);

        // Zajistíme, že uživatel (UserEntity) existuje, předpokládáme, že uživatel je již vytvořen a propojen s pojištěncem
        UserEntity userEntity = userRepository.findById(insuredPersonDTO.getInsuredPersonId())
                .orElseThrow(() -> new RuntimeException("Uživatel nenalezen"));

        insuredPersonEntity.setUserEntity(userEntity);

        // Uložení pojištěnce do databáze
        insuredPersonRepository.save(insuredPersonEntity);
    }


}
