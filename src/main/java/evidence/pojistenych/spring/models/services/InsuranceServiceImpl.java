package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.repository.InsuranceRepository;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.CreateRecordFormData;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.PageDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.exceptions.InsuranceNotFoundException;
import evidence.pojistenych.spring.models.exceptions.InsuredPersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;


@Service
public class InsuranceServiceImpl implements InsuranceService {

    private static final Logger log = LoggerFactory.getLogger(InsuranceServiceImpl.class);

    @Autowired
    private InsuranceRepository insuranceRepository;


    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuredPersonRepository insuredPersonRepository;

    @Autowired
    private InsuredPersonService insuredPersonService;


    /**
     * Vytvoří nový záznam o pojištění pro daného pojištěnce.
     * Nejprve provede validaci vstupních dat, poté načte entitu pojištěnce,
     * převede DTO na entitu, propojí ji s pojištěncem a uloží do databáze.
     *
     * @param insuranceRecordDTO DTO objekt s daty pojištění
     * @param insuredPersonId ID pojištěnce, ke kterému se pojištění vztahuje
     * @param bindingResult Výsledek validačních kontrol (případné chyby se zde ukládají)
     */
    @Override
    public void  createInsuranceRecord(InsuranceRecordDTO insuranceRecordDTO, Long insuredPersonId, BindingResult bindingResult){

        validateCreateInputs(insuranceRecordDTO, insuredPersonId, bindingResult);
        InsuredPersonEntity insuredPersonEntity = getInsuredPersonOrThrow(insuredPersonId);

        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceRecordDTO);
        insuranceEntity.setInsuredPerson(insuredPersonEntity);
        insuranceRepository.save(insuranceEntity);
    }

    /**
     * Připraví data pro formulář pro vytvoření nového záznamu o pojištění.
     * Načte pojištěnce podle ID a vytvoří prázdné DTO pro nový záznam,
     * do kterého nastaví ID pojištěnce. Obě data zabalí do objektu `CreateRecordFormData`.
     *
     * @param insuredPersonId ID pojištěnce, pro kterého se formulář připravuje
     * @return Objekt obsahující data potřebná pro zobrazení formuláře
     */
    @Override
    public CreateRecordFormData prepareCreateRecordForm(Long insuredPersonId) {
        InsuredPersonDTO insuredPersonDTO = insuredPersonService.getById(insuredPersonId);
        InsuranceRecordDTO insuranceRecordDTO = new InsuranceRecordDTO();
        insuranceRecordDTO.setInsuredPersonId(insuredPersonId);
        return new CreateRecordFormData(insuredPersonDTO, insuranceRecordDTO);
    }

    /**
     * Vrací detail pojištěnce včetně stránkovaného seznamu jeho pojištění.
     * Načte pojištěnce podle ID a pomocí repository získá stránkovaná pojištění
     * svázaná s tímto pojištěncem. Výsledek vrací zabalený v objektu `PageDTO`.
     *
     * @param insuranceId ID pojištěnce, jehož detail se má zobrazit
     * @param page Číslo požadované stránky (0-based index)
     * @param size Počet záznamů na stránku
     * @return PageDTO obsahující pojištěnce, seznam pojištění a informace o stránkování
     */
    @Override
    public PageDTO getInsuredPersonDetail(Long insuranceId, int page, int size){
        InsuredPersonEntity insuredPersonEntity = insuredPersonRepository.findById(insuranceId)
                .orElseThrow(()-> new RuntimeException("Pojištěnec nenalezen"));

        Pageable pageable = PageRequest.of(page, size);
        Page<InsuranceEntity> insuranceEntityPage = insuranceRepository.findByInsuredPerson(insuredPersonEntity, pageable);

        return new PageDTO(
                insuredPersonEntity,
                insuranceEntityPage.getContent(),
                page,
                insuranceEntityPage.getTotalPages(),
                insuranceEntityPage.getTotalElements()
        );
    }

    /**
     * Upraví existující záznam o pojištění na základě dat z DTO.
     * Nejprve načte existující entitu pojištění podle ID z DTO,
     * následně provede aktualizaci pomocí mapperu a změny uloží do databáze.
     *
     * @param insuranceRecordDTO DTO obsahující upravená data pojištění, včetně ID existujícího záznamu
     */
    @Override
    public void edit(InsuranceRecordDTO insuranceRecordDTO){
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceRecordDTO.getInsuranceId());

        insuranceMapper.updateInsuranceEntity(insuranceRecordDTO, fetchedInsurance);
        insuranceRepository.save(fetchedInsurance);
    }

    /**
     * Odstraní záznam o pojištění na základě jeho ID.
     * Nejprve načte existující entitu pojištění podle ID a následně ji odstraní z databáze.
     *
     * @param insuranceId ID pojištění, které má být odstraněno
     */
    @Override
    public  void remove(long insuranceId){
        InsuranceEntity fetchedEntity = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(fetchedEntity);
    }

    /**
     * Načte záznam o pojištění podle jeho ID a vrátí ho jako DTO.
     * Pokud pojištění s daným ID neexistuje, vyvolá výjimku.
     *
     * @param insuranceId ID pojištění, které má být načteno
     * @return DTO reprezentující pojištění
     * @throws RuntimeException Pokud pojištění s daným ID nebylo nalezeno
     */
    @Override
    public InsuranceRecordDTO getById(Long insuranceId) {
        // Zavoláme repository, abychom získali pojištění podle ID
        InsuranceEntity fetchedInsurance = insuranceRepository.findById(insuranceId)
                .orElseThrow(() -> new RuntimeException("Pojištění s ID " + insuranceId + " nenalezeno"));

        // Vrátíme DTO verzi pojištění, kterou použijeme ve view
        return insuranceMapper.toDTO(fetchedInsurance);
    }

    /**
     * Načte pojištění podle jeho ID. Pokud pojištění s daným ID neexistuje, vyvolá vlastní výjimku.
     *
     * @param insuranceId ID pojištění, které má být načteno
     * @return Načtená entita pojištění
     * @throws InsuranceNotFoundException Pokud pojištění s daným ID nebylo nalezeno
     */
    private InsuranceEntity getInsuranceOrThrow(long insuranceId){
        return insuranceRepository
                .findById(insuranceId)
                .orElseThrow(InsuranceNotFoundException::new);
    }

    /**
     * Validuje vstupní data pro vytvoření záznamu o pojištění.
     * Pokud jsou vstupní data neplatná (tj. `dto` nebo `personId` jsou null),
     * přidá chybu do `BindingResult`, která bude indikovat neplatnost těchto hodnot.
     *
     * @param dto DTO obsahující data pojištění, která mají být validována
     * @param personId ID pojištěnce, ke kterému se pojištění vztahuje
     * @param bindingResult Výsledek validačních kontrol, kde se ukládají chyby
     */
    private void validateCreateInputs(InsuranceRecordDTO dto, Long personId, BindingResult bindingResult) {
        if (dto == null || personId == null) {
            bindingResult.rejectValue("insuranceRecordDTO", "error", "Pojištění nebo pojištěnec nejsou validní");
        }
    }

    /**
     * Načte pojištěnce podle jeho ID. Pokud pojištěnec s daným ID neexistuje, vyvolá vlastní výjimku.
     *
     * @param id ID pojištěnce, který má být načten
     * @return Načtená entita pojištěnce
     * @throws InsuredPersonNotFoundException Pokud pojištěnec s daným ID nebyl nalezen
     */
    private InsuredPersonEntity getInsuredPersonOrThrow(Long id) {
        return insuredPersonRepository.findById(id)
                .orElseThrow(() -> new InsuredPersonNotFoundException(id));
    }
}
