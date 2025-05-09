package evidence.pojistenych.spring.models;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;

public class CreateRecordFormData {

    private InsuredPersonDTO insuredPerson;
    private InsuranceRecordDTO insuranceRecordDTO;

    public CreateRecordFormData(InsuredPersonDTO insuredPerson, InsuranceRecordDTO insuranceRecordDTO) {
        this.insuredPerson = insuredPerson;
        this.insuranceRecordDTO = insuranceRecordDTO;
    }

    public InsuredPersonDTO getInsuredPerson() {
        return insuredPerson;
    }

    public InsuranceRecordDTO getInsuranceRecordDTO() {
        return insuranceRecordDTO;
    }
}
