package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;

import java.util.List;

public interface InsuranceService {

    /**
     *
     * @param insuranceRecordDTO
     */
    void create(InsuranceRecordDTO insuranceRecordDTO);

    /**
     *
     * @param insuranceId
     * @return
     */
    InsuranceRecordDTO getById(Long insuranceId);

    /**
     *
     * @param insuranceRecordDTO
     */
    void edit(InsuranceRecordDTO insuranceRecordDTO);

    /**
     *
     * @param insuranceId
     */
    void remove(long insuranceId);
}
