package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import evidence.pojistenych.spring.data.repository.InsuranceRepository;
import evidence.pojistenych.spring.data.repository.InsuredPersonRepository;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.mappers.InsuranceMapper;
import evidence.pojistenych.spring.models.exceptions.InsuranceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    private static final Logger log = LoggerFactory.getLogger(InsuranceServiceImpl.class);
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

    @Autowired
    private InsuredPersonRepository insuredPersonRepository;


    /**
     *
     * @param
     */
    @Transactional
    @Override
    public void create(InsuranceRecordDTO insuranceRecordDTO){
        log.debug("Metoda create() byla zavolána s DTO: {}", insuranceRecordDTO);
        if (insuranceRecordDTO == null) {
            throw new IllegalArgumentException("Pojištění nesmí být null");
        }

        if(insuranceRecordDTO.getInsuredPersonId()==null){
            throw new IllegalArgumentException("Pojištěnec není přiřazen k pojištění");
        }
        

        InsuranceEntity insuranceEntity = insuranceMapper.toEntity(insuranceRecordDTO);

        InsuredPersonEntity insuredPersonEntity = insuredPersonRepository.findById(insuranceRecordDTO.getInsuredPersonId())
                        .orElseThrow(()-> new RuntimeException("Pojištěnec s ID " + insuranceRecordDTO.getInsuredPersonId() + " nenalezen"));


        insuranceEntity.setInsuredPerson(insuredPersonEntity);

        insuranceRepository.save(insuranceEntity);


        log.debug("Pojištění vytvořeno: {}", insuranceEntity);
    }


    /**
     *
     * @param insuranceId
     * @return
     */
    @Override
    public InsuranceRecordDTO getById(Long insuranceId){
        InsuranceEntity fetchedInsurance = insuranceRepository
                .findById(insuranceId)
                .orElseThrow();
        return insuranceMapper.toDTO(fetchedInsurance);
    }

    /**
     *
     * @param insuranceRecordDTO
     */
    @Override
    public  void edit(InsuranceRecordDTO insuranceRecordDTO){
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insuranceRecordDTO.getInsuranceId());

        insuranceMapper.updateInsuranceEntity(insuranceRecordDTO, fetchedInsurance);
        insuranceRepository.save(fetchedInsurance);
    }

    /**
     *
     * @param insuranceId
     */
    @Override
    public  void remove(long insuranceId){
        InsuranceEntity fetchedEntity = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(fetchedEntity);
    }


    /**
     *
     * @param insuranceId
     * @return
     */
    private InsuranceEntity getInsuranceOrThrow(long insuranceId){
        return insuranceRepository
                .findById(insuranceId)
                .orElseThrow(InsuranceNotFoundException::new);
    }
}
