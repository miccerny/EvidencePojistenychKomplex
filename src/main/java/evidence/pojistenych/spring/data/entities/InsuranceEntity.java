package evidence.pojistenych.spring.data.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "insured_person_entity")
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insuranceId;

    @Column(nullable = false)
    private String insurance;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String subjectOfInsurance;

    @Column(nullable = false, name = "start_date")
    private LocalDate dateFrom;

    @Column(nullable = false, name = "end_date")
    private LocalDate dateTo;

    @ManyToOne
    @JoinColumn(name = "insured_person_id", referencedColumnName = "id")
    private InsuredPersonEntity insuredPerson;

    public InsuredPersonEntity getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(InsuredPersonEntity insuredPerson) {
        this.insuredPerson = insuredPerson;
    }




    public Long getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Long insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = BigDecimal.valueOf(amount);
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
