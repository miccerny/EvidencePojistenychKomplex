package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.CreateRecordFormData;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.PageDTO;
import org.springframework.validation.BindingResult;


public interface InsuranceService {

    /**
     *
     * @param insuranceRecordDTO
     */
    CreateRecordFormData prepareCreateRecordForm (Long insuredPersonId);

    void  createInsuranceRecord(InsuranceRecordDTO insuranceRecordDTO, Long insuredPersonId, BindingResult bindingResult);
    /**
     *
     * @param insuranceId
     * @return
     */


    PageDTO getInsuredPersonDetail(Long insuranceId, int page, int size);

    /**
     *
     * @param insuranceRecordDTO
     */
    void edit(InsuranceRecordDTO insuranceRecordDTO);

    InsuranceRecordDTO getById(Long insuranceId);

    /**
     *
     * @param insuranceId
     */
    void remove(long insuranceId);
}
