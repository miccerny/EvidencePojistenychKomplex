package evidence.pojistenych.spring.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Třída reprezentuje entitu pojištění v databázi.
 * *
 * Je mapována na tabulku "insurance_entity".
 */
@Entity
@Table(name = "insurance_entity")
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long id; // ID pojištění, automaticky generované

    @Column(nullable = false, name = "insurance")
    private String insurance;  // Název pojištění

    @Column(nullable = false, name = "amount")
    private BigDecimal amount; // Částka pojištění

    @Column(nullable = false, name = "subject_Of_Insurance")
    private String subjectOfInsurance; // Předmět pojištění

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(nullable = false, name = "start_date")
    private LocalDate dateFrom; // Datum začátku pojištění

    @JsonFormat(pattern = "dd.MM.yyyy")
    @Column(nullable = false, name = "end_date")
    private LocalDate dateTo; // Datum konce pojištění


    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private InsuredPersonEntity insuredPerson; // Pojištěná osoba, ke které pojištění patří

    /**
     * Vrátí pojištěnou osobu.
     * @return - pojištěná osoba
     */
    public InsuredPersonEntity getInsuredPerson() {
        return insuredPerson;
    }

    /**
     * Nastaví pojištěnou osobu.
     * @param insuredPerson - pojištěná osoba
     */
    public void setInsuredPerson(InsuredPersonEntity insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    /**
     * Vrátí ID pojištění.
     * @return - ID pojištění
     */
    public Long getInsuranceId() {
        return id;
    }

    /**
     * Nastaví ID pojištění.
     * @param insuranceId - ID pojištění
     */
    public void setInsuranceId(Long insuranceId) {
        this.id = insuranceId;
    }

    /**
     * Vrátí název pojištění.
     * @return - název pojištění
     */
    public String getInsurance() {
        return insurance;
    }

    /**
     * Nastaví název pojištění.
     * @param insurance - název pojištění
     */
    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    /**
     * Vrátí částku pojištění.
     * @return - částka pojištění
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Nastaví částku pojištění.
     * @param amount - částka pojištění jako double
     */
    public void setAmount(double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }

    /**
     * Vrátí předmět pojištění.
     * @return předmět pojištění
     */
    public String getSubjectOfInsurance() {
        return subjectOfInsurance;
    }

    /**
     * Nastaví předmět pojištění.
     * @param subjectOfInsurance předmět pojištění
     */
    public void setSubjectOfInsurance(String subjectOfInsurance) {
        this.subjectOfInsurance = subjectOfInsurance;
    }

    /**
     * Vrátí datum začátku pojištění.
     * @return datum začátku pojištění
     */
    public LocalDate getDateFrom() {
        return dateFrom;
    }

    /**
     * Nastaví datum začátku pojištění.
     * @param dateFrom datum začátku pojištění
     */
    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    /**
     * Vrátí datum konce pojištění.
     * @return datum konce pojištění
     */
    public LocalDate getDateTo() {
        return dateTo;
    }

    /**
     * Nastaví datum konce pojištění.
     * @param dateTo datum konce pojištění
     */
    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }



}
