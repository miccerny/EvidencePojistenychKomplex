package evidence.pojistenych.spring.models.dto;


import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

/**
 * DTO pro přenos dat o pojistné smlouvě.
 * Obsahuje informace o typu pojištění, částce, předmětu pojištění,
 * platnosti pojištění a ID pojištěné osoby.
 *
 * Validace polí zajišťují správnost a úplnost dat:
 * - insurance: musí být vyplněno (typ pojištění)
 * - amount: kladná částka, nesmí být nula
 * - subjectOfInsurance: minimálně 10 znaků, nesmí být prázdné
 * - dateFrom: datum začátku platnosti pojištění, nesmí být v minulosti
 * - dateTo: datum konce platnosti, musí být v budoucnosti
 * - insuredPersonId: musí být vyplněno (identifikátor pojištěnce)
 */
public class InsuranceRecordDTO {

    private Long insuranceId;

    @NotBlank(message = "Vyplňte typ pojištění")
    private String insurance;

    @NotNull(message = "Částka nesmí být nula")
    @Positive(message = "Částká musí být vyplněna a kladné číslo")
    private double amount;

    @Size(min = 10, message = "Předmět musí mít alespoň 10 znaků")
    @NotBlank(message = "Vyplňte předmět")
    private String subjectOfInsurance;

    @NotNull(message = "Nesmí být prázdné")
    @FutureOrPresent(message = "Musí být datum v budoucnosti nebo dnešní")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateFrom;


    @NotNull(message = "Nesmí být prázdné")
    @Future(message = "Musí být datum v budoucnosti")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateTo;

    @NotNull
    private Long insuredPersonId;


    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public Long getInsuredPersonId() {
        return insuredPersonId;
    }

    public void setInsuredPersonId(Long insuredPersonId) {
        this.insuredPersonId = insuredPersonId;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getSubjectOfInsurance() {
        return subjectOfInsurance;
    }

    public void setSubjectOfInsurance(String subjectOfInsurance) {
        this.subjectOfInsurance = subjectOfInsurance;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }


}
