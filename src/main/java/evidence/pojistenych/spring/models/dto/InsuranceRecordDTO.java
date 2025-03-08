package evidence.pojistenych.spring.models.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class InsuranceRecordDTO {

    private long insuranceId;

    @NotBlank(message = "Vyplňte typ pojištění")
    private String insurance;

    @NotNull
    @Positive(message = "Částká musí být vyplněna a kladné číslo")
    private double amount;

    @Size(min = 10, message = "Předmět musí mít alespoň 10 znaků")
    @NotBlank(message = "Vyplňte předmět")
    private String subjectOfInsurance;

    @NotNull
    @FutureOrPresent(message = "Musí být datum v budoucnosti nebo dnešní")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateFrom;

    /**
     *
     */
    @NotNull
    @Future(message = "Musí být datum v budoucnosti")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate dateTo;

    public long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(long insuranceId) {
        this.insuranceId = insuranceId;
    }

    /**
     *
     * @return
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     *
     * @param insurance
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    /**
     *
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public String getSubjectOfInsurance() {
        return subjectOfInsurance;
    }

    /**
     *
     * @param subjectOfInsurance
     */
    public void setSubjectOfInsurance(String subjectOfInsurance) {
        this.subjectOfInsurance = subjectOfInsurance;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    /**
     *
     * @param dateFrom
     */
    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     *
     * @return
     */
    public LocalDate getDateTo() {
        return dateTo;
    }

    /**
     *
     * @param dateTo
     */
    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }


}
