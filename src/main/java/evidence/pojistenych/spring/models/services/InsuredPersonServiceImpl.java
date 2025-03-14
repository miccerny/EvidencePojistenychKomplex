package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.InsuranceRecordsApplication;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuredPersonMapper;
import evidence.pojistenych.spring.models.exceptions.InsuredNotFoundExcption;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void create(InsuredPersonDTO insuredPersonDTO) {
        InsuredPersonEntity insuredPersonEntity = insuredPersonMapper.toEntity(insuredPersonDTO);
         insuredPersonRepository.save(insuredPersonEntity);
    }

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

    public InsuredPersonDTO getById(Long id) {
        return insuredPersonRepository.findById(id)
                .map(insuredPersonMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void edit(InsuredPersonDTO insuredPersonDTO) {
        InsuredPersonEntity fetchedInsuredPerson = getInsuredPersonOrThrow(insuredPersonDTO.getId());

        insuredPersonMapper.updateInsuredPersonEntity(insuredPersonDTO, fetchedInsuredPerson);
        insuredPersonRepository.save(fetchedInsuredPerson);
    }

    public void delete(Long id) {
        insuredPersonRepository.deleteById(id);
    }

    private InsuredPersonEntity getInsuredPersonOrThrow(Long id){
        return insuredPersonRepository.findById(id)
                .orElseThrow(InsuredNotFoundExcption::new);

    }
}
