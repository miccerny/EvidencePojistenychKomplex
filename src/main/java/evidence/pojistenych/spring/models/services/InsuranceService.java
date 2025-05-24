package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.models.CreateRecordFormData;
import evidence.pojistenych.spring.models.dto.InsuranceRecordDTO;
import evidence.pojistenych.spring.models.dto.PagedInsuranceDTO;
import org.springframework.validation.BindingResult;


public interface InsuranceService {

    /**
     * Připraví a vrátí potřebná datapro vytvoření nového záznamu o pojištění
     *  * pro zadanou pojištěnou osobu.
     * @param insuredPersonId ID pojištěné osoby, pro kterou se formulář připravuje
     * @return objekt obsahující data potřebná pro vytvoření záznamu
     */
    CreateRecordFormData prepareCreateRecordForm (Long insuredPersonId);

    /**
     * Vytvoří nový záznam o pojištění pro danou pojištěnou osobu.
     * Provádí také validaci dat a výsledky validace ukládá do BindingResult.
     *
     * @param insuranceRecordDTO data z formuláře pro vytvoření pojištění
     * @param insuredPersonId ID pojištěné osoby, ke které se pojištění vztahuje
     * @param bindingResult objekt pro zaznamenání chyb validace
     */
    void  createInsuranceRecord(InsuranceRecordDTO insuranceRecordDTO, Long insuredPersonId, BindingResult bindingResult);


    /**
     * Vrátí detailní informace o pojištěné osobě podle ID pojištění,
     * včetně stránkovaného seznamu jejích pojištění.
     *
     * @param insuranceId ID pojištění, podle kterého se hledá pojištěná osoba
     * @param page číslo stránky (index od 0)
     * @param size počet záznamů na stránku
     * @return data o pojištěné osobě a stránkované pojištění
     */
    PagedInsuranceDTO getInsuredPersonDetail(Long insuranceId, int page, int size);

    /**
     * Upraví existující záznam o pojištění na základě poskytnutých dat.
     *
     * @param insuranceRecordDTO data pro úpravu záznamu o pojištění
     */
    void edit(InsuranceRecordDTO insuranceRecordDTO);

    /**
     * Vrátí data záznamu pojištění podle jeho ID.
     *
     * @param insuranceId ID záznamu pojištění
     * @return data pojištění
     */
    InsuranceRecordDTO getById(Long insuranceId);

    /**
     * Odstraní záznam o pojištění podle ID.
     *
     * @param insuranceId ID záznamu pojištění k odstranění
     */
    void remove(long insuranceId);
}
