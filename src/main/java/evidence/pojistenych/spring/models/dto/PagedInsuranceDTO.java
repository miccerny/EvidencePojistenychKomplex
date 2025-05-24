package evidence.pojistenych.spring.models.dto;

import evidence.pojistenych.spring.data.entities.InsuranceEntity;
import evidence.pojistenych.spring.data.entities.InsuredPersonEntity;
import java.util.List;

/**
 * Třída slouží k přenosu dat o stránkovaném seznamu pojištění jedné pojištěné osoby.
 */
public class PagedInsuranceDTO {
    /**
     * Informace o pojištěné osobě.
     */
    private InsuredPersonEntity insuredPerson;
    /**
     * Seznam pojištění spojených s pojištěnou osobou.
     */
    private List<InsuranceEntity> insurances;

    /**
     * Aktuální číslo stránky.
     */
    private int currentPage;

    /**
     * Celkový počet stránek.
     */
    private int totalPages;

    /**
     * Celkový počet položek (pojištění).
     */
    private long totalItems;

    /**
     * Konstruktor třídy pro nastavení všech hodnot.
     *
     * @param insuredPerson pojištěná osoba
     * @param insurances seznam pojištění
     * @param currentPage aktuální stránka
     * @param totalPages celkový počet stránek
     * @param totalItems celkový počet záznamů
     */
    public PagedInsuranceDTO(InsuredPersonEntity insuredPerson, List<InsuranceEntity> insurances, int currentPage, int totalPages, long totalItems) {
        this.insuredPerson = insuredPerson;
        this.insurances = insurances;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    /**
     * Vrací pojištěnou osobu.
     * @return pojištěná osoba
     */
    public InsuredPersonEntity getInsuredPerson() {
        return insuredPerson;
    }


    /**
     * Nastaví pojištěnou osobu.
     * @param insuredPerson pojištěná osoba
     */
    public void setInsuredPerson(InsuredPersonEntity insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    /**
     * Vrací seznam pojištění.
     * @return seznam pojištění
     */
    public List<InsuranceEntity> getInsurances() {
        return insurances;
    }

    /**
     * Vrací aktuální stránku.
     * @return aktuální stránka
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * Vrací celkový počet stránek.
     * @return počet stránek
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Vrací celkový počet záznamů.
     * @return počet záznamů
     */
    public long getTotalItems() {
        return totalItems;
    }
}
