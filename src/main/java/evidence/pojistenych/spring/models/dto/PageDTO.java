package evidence.pojistenych.spring.models.dto;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;

import java.util.List;

public class PageDTO {
    private InsuredPersonEntity insuredPerson;
    private List<InsuranceEntity> insurances;
    private int currentPage;
    private int totalPages;
    private long totalItems;

    public PageDTO(InsuredPersonEntity insuredPerson, List<InsuranceEntity> insurances, int currentPage, int totalPages, long totalItems) {
        this.insuredPerson = insuredPerson;
        this.insurances = insurances;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public InsuredPersonEntity getInsuredPerson() {
        return insuredPerson;
    }

    public void setInsuredPerson(InsuredPersonEntity insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public List<InsuranceEntity> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<InsuranceEntity> insurances) {
        this.insurances = insurances;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }
}
