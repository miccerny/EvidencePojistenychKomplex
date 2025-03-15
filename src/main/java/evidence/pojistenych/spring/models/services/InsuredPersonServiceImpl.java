package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.InsuranceRecordsApplication;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuredPersonMapper;
import evidence.pojistenych.spring.models.exceptions.InsuredNotFoundExcption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class InsuredPersonServiceImpl implements InsuredPersonService {

    @Autowired
    InsuredPersonMapper insuredPersonMapper;

    @Autowired
    InsuredPersonRepository insuredPersonRepository;

    /**
     * Servisní metoda pro vytvoření nového pojištěnce
     * @param insuredPersonDTO
     */
    public void create(InsuredPersonDTO insuredPersonDTO) {
        InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonDTO);
         insuredPersonRepository.save(insuredPersonEntity);
    }

    /**
     *Metoda pro vyhledání všech pojištěnců v datbáti
     * @return - vrátí všechny pojištěnce
     */

    public List<InsuredPersonDTO> getAll() {

        List<InsuredPersonDTO> insuredList = StreamSupport.stream(insuredPersonRepository.findAll().spliterator(), false)
                .map(i -> insuredPersonMapper.toDTO(i))
                .toList();

        // Debug výpis
        for (InsuredPersonDTO person : insuredList) {
            System.out.println("ID: " + person.getId() + ", Jméno: " + person.getFirstName());
        }
        return  insuredList;
    }

    /**
     * Metoda pro vyhledání konkrétního pojištěnce v databázi
     * @param id - identifikace pojištěnce v databázi (entita)
     * @return - vrátí konkrétního pojištěnce
     */
    public InsuredPersonDTO getById(Long id) {
        return insuredPersonRepository.findById(id)
                .map(insuredPersonMapper::toDTO)
                .orElse(null);
    }

    /**
     * Servisní Metoda pro úpravu pojištěnce v databázi
     * @param insuredPersonDTO
     */
    @Override
    public void edit(InsuredPersonDTO insuredPersonDTO) {
        InsuredPersonEntity fetchedInsuredPerson = getInsuredPersonOrThrow(insuredPersonDTO.getId());

        insuredPersonMapper.updateInsuredPersonEntity(insuredPersonDTO, fetchedInsuredPerson);
        insuredPersonRepository.save(fetchedInsuredPerson);
    }

    /**
     * Servisní metoda pro smazání konkrétního pojištěnce z databáze
     * @param id
     */
    @Override
    public void delete(Long id) {
        insuredPersonRepository.deleteById(id);
    }

    /**
     * Metoda, která vyvolá chybu v případě nenaleezení pojištěnce
     * @param id
     * @return
     */
    private InsuredPersonEntity getInsuredPersonOrThrow(Long id){
        return insuredPersonRepository.findById(id)
                .orElseThrow(InsuredNotFoundExcption::new);

    }
}
