package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.repository.InsuranceRepository;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.exceptions.InsuranceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    /**
     *
     */
    @Autowired
    private InsuranceRepository insuranceRepository;


    /**
     *
     */
    @Autowired
    private InsuranceMapper insuranceMapper;

    /**
     *
     * @param insuranceRecordDTO
     */
    @Override
    public void create(InsuranceRecordDTO insuranceRecordDTO){
        InsuranceEntity newInsurance = insuranceMapper.toEntity(insuranceRecordDTO);

        insuranceRepository.save(newInsurance);

    }

    @Override
    public List<InsuranceRecordDTO> getAll(){
        List<InsuranceRecordDTO> result = StreamSupport.stream(insuranceRepository.findAll().spliterator(), false)
                .map(insuranceEntity -> insuranceMapper.toDTO(insuranceEntity)).toList();

        System.out.println("Pojištění v databázi: " + result.size() + " záznamů.");
        return result;
    }

    @Override
    public InsuranceRecordDTO getById(long insuranceId){
        InsuranceEntity fetchedInsurance = insuranceRepository
                .findById(insuranceId)
                .orElseThrow();
        return insuranceMapper.toDTO(fetchedInsurance);
    }

    @Override
    public  void edit(InsuranceRecordDTO insuranceRecordDTO){
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceRecordDTO.getInsuranceId());

        insuranceMapper.updateInsuranceEntity(insuranceRecordDTO, fetchedInsurance);
        insuranceRepository.save(fetchedInsurance);
    }

    @Override
    public  void remove(long insuranceId){
        InsuranceEntity fetchedEntity = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(fetchedEntity);
    }




    private InsuranceEntity getInsuranceOrThrow(long insuranceId){
        return insuranceRepository
                .findById(insuranceId)
                .orElseThrow(InsuranceNotFoundException::new);
    }
}
