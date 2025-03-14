package evidence.pojistenych.spring.data.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 */
@Entity
@Table(name = "insurance_entity")
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insurance_id")
    private Long id;

    @Column(nullable = false, name = "insurance")
    private String insurance;

    @Column(nullable = false, name = "amount")
    private BigDecimal amount;

    @Column(nullable = false, name = "subject_Of_Insurance")
    private String subjectOfInsurance;

    @Column(nullable = false, name = "start_date")
    private LocalDate dateFrom;

    @Column(nullable = false, name = "end_date")
    private LocalDate dateTo;


    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private InsuredPersonEntity insuredPerson;

    /**
     *
     * @return
     */
    public InsuredPersonEntity getInsuredPerson() {
        return insuredPerson;
    }

    /**
     *
     * @param insuredPerson
     */
    public void setInsuredPerson(InsuredPersonEntity insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    /**
     *
     * @return
     */
    public Long getInsuranceId() {
        return id;
    }

    /**
     *
     * @param insuranceId
     */
    public void setInsuranceId(Long insuranceId) {
        this.id = insuranceId;
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
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = BigDecimal.valueOf(amount);
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
