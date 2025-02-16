package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;

import java.util.List;

public interface InsuranceService {

    List<InsuranceRecordDTO> getAll();

    void create(InsuranceRecordDTO insuranceRecordDTO);
}
