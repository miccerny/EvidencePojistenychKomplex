package evidence.pojistenych.spring.models.services;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.data.repository.UserRepository;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.UserDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.dto.mappers.InsuredPersonMapper;
import evidence.pojistenych.spring.models.dto.mappers.UserMapper;
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
    private InsuredPersonMapper insuredPersonMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public InsuredPersonEntity create(InsuredPersonDTO insuredPersonDTO) {
        // Vytvoření nové entity pojištěnce
        UserDTO userDTO = insuredPersonDTO.getUserDTO();

        // Kontrola platnosti e-mailu
        if (userDTO == null || userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email uživatele není platný nebo chybí.");
        }

        // Hledání uživatele v databázi podle emailu
        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail());

        // Pokud uživatel neexistuje, vytvoříme nového
        if (userEntity == null) {
            userEntity = userMapper.toEntity(userDTO);
            userRepository.save(userEntity);
        }

        // Mapování DTO na entity pojištěnce
        InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonDTO);
        insuredPersonEntity.setUserEntity(userEntity); // Propojení pojištěnce s uživatelem

        // Uložení pojištěnce do databáze
        return insuredPersonRepository.save(insuredPersonEntity);
    }


}
