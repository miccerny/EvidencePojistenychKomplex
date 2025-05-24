package evidence.pojistenych.spring.models;

import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.InsuredPersonDTO;

/**
 * Třída sloužící jako datový kontejner pro vytvoření nového záznamu.
 * Obsahuje informace o pojištěnci i související pojistný záznam.
 */
public class CreateRecordFormData {

    /**
     * Data pojištěnce (DTO).
     */
    private InsuredPersonDTO insuredPerson;

    /**
     * Data pojistného záznamu (DTO).
     */
    private InsuranceRecordDTO insuranceRecordDTO;

    /**
     * Konstruktor pro inicializaci obou částí formuláře.
     *
     * @param insuredPerson Data pojištěnce
     * @param insuranceRecordDTO Data pojistného záznamu
     */
    public CreateRecordFormData(InsuredPersonDTO insuredPerson, InsuranceRecordDTO insuranceRecordDTO) {
        this.insuredPerson = insuredPerson;
        this.insuranceRecordDTO = insuranceRecordDTO;
    }


    /**
     * Vrací data pojištěnce.
     *
     * @return InsuredPersonDTO
     */
    public InsuredPersonDTO getInsuredPerson() {
        return insuredPerson;
    }

    /**
     * Vrací data pojistného záznamu.
     *
     * @return InsuranceRecordDTO
     */
    public InsuranceRecordDTO getInsuranceRecordDTO() {
        return insuranceRecordDTO;
    }
}
