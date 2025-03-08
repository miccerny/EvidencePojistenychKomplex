package evidence.pojistenych.spring.models.services;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.data.repository.UserRepository;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.dto.mappers.InsuredPersonMapper;
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
    InsuredPersonMapper insuredPersonMapper;

    @Override
    public InsuredPersonEntity create(InsuredPersonDTO insuredPersonDTO) {
        // Vytvoření nové entity pojištěnce


        // Zajistíme, že uživatel (UserEntity) existuje, předpokládáme, že uživatel je již vytvořen a propojen s pojištěncem
       UserEntity userEntity = userRepository.findByEmail(insuredPersonDTO.getUserDTO().getEmail())
                        .orElseThrow(()-> new RuntimeException("Uživatel nenalezen"));

        if (userEntity == null) {
            userEntity = new UserEntity();
            userEntity.setEmail(insuredPersonDTO.getEmail());
            // Další nastavení pro uživatele
            userRepository.save(userEntity);
        }
        InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonDTO);
        insuredPersonEntity.setUserEntity(userEntity);

        // Uložení pojištěnce do databáze
        return insuredPersonRepository.save(insuredPersonEntity);
    }


}
