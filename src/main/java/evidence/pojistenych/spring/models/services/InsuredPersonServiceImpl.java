package evidence.pojistenych.spring.models.services;


import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuredPersonMapper;
import evidence.pojistenych.spring.models.exceptions.DuplicateEmailException;
import evidence.pojistenych.spring.models.exceptions.InsuredPersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class InsuredPersonServiceImpl implements InsuredPersonService {

    @Autowired
    InsuredPersonMapper insuredPersonMapper;

    @Autowired
    InsuredPersonRepository insuredPersonRepository;

    /**
     * Vytvoří nového pojištěnce na základě dat z DTO.
     * Nejprve provede kontrolu, zda již neexistuje pojištěnec se stejným emailem.
     * Pokud ano, vyvolá výjimku `DataIntegrityViolationException`.
     * Pokud ne, převede DTO na entitu a uloží nového pojištěnce do databáze.
     *
     * @param insuredPersonDTO DTO obsahující data pro vytvoření nového pojištěnce
     */
    @Override
    public void create(InsuredPersonDTO insuredPersonDTO) {
        checkEmailUnique(insuredPersonDTO.getEmail());
        InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonDTO);
         insuredPersonRepository.save(insuredPersonEntity);
    }

    /**
     * Načte všechny pojištěnce a vrátí je jako seznam DTO objektů.
     * Používá streamování pro efektivní převod entit na DTO objekty.
     * Výsledek se vypisuje do konzole pro ladicí účely.
     *
     * @return Seznam DTO objektů představujících všechny pojištěnce
     */
    @Override
    public List<InsuredPersonDTO> getAll() {

        List<InsuredPersonDTO> insuredList = StreamSupport.stream(insuredPersonRepository.findAll().spliterator(), false)
                .map(i -> insuredPersonMapper.toDTO(i))
                .toList();

        return  insuredList;
    }

    /**
     * Načte pojištěnce podle jeho ID a vrátí ho jako DTO.
     * Pokud pojištěnec s daným ID neexistuje, vrátí `null`.
     *
     * @param id ID pojištěnce, který má být načten
     * @return DTO objekt reprezentující pojištěnce, nebo `null`, pokud pojištěnec s daným ID neexistuje
     */
    @Override
    public InsuredPersonDTO getById(Long id) {
        return insuredPersonRepository.findById(id)
                .map(insuredPersonMapper::toDTO)
                .orElse(null);
    }

    /**
     * Upraví existujícího pojištěnce na základě dat z DTO.
     * Nejprve načte existující entitu pojištěnce podle ID z DTO,
     * následně provede aktualizaci entity pomocí mapperu a změny uloží do databáze.
     *
     * @param insuredPersonDTO DTO obsahující aktualizovaná data pojištěnce, včetně ID existujícího záznamu
     */
    @Override
    public void edit(InsuredPersonDTO insuredPersonDTO) {

        InsuredPersonEntity existingPerson = getInsuredPersonOrThrow(insuredPersonDTO.getId());

        insuredPersonMapper.updateInsuredPersonEntity(insuredPersonDTO, existingPerson);
        insuredPersonRepository.save(existingPerson);
    }

    /**
     * Odstraní pojištěnce podle jeho ID z databáze.
     * Pokud pojištěnec s daným ID neexistuje, nebude provedena žádná akce.
     *
     * @param id ID pojištěnce, který má být odstraněn
     */
    @Override
    public void delete(Long id) {
        insuredPersonRepository.deleteById(id);
    }

    /**
     * Načte pojištěnce podle jeho ID. Pokud pojištěnec s daným ID neexistuje, vyvolá výjimku.
     *
     * @param id ID pojištěnce, který má být načten
     * @return Načtená entita pojištěnce
     */
    private InsuredPersonEntity getInsuredPersonOrThrow(Long id){
        return insuredPersonRepository.findById(id)
                .orElseThrow(() -> new InsuredPersonNotFoundException(id));

    }

    /**
     * Zkontroluje, zda email již není v databázi.
     *
     * @param email Email pojištěnce k prověření.
     * @throws DuplicateEmailException Pokud email již existuje.
     */
    private void checkEmailUnique(String email) {
        if (insuredPersonRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("Email je již registrován.");
        }
    }
}
