package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;

import java.util.List;

public interface InsuranceService {

    void create(InsuranceRecordDTO insuranceRecordDTO);

    InsuranceRecordDTO getById(Long insuranceId);

    void edit(InsuranceRecordDTO insuranceRecordDTO);

    void remove(long insuranceId);
}
