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
     * Vytvoří nový záznam o pojištění pro danou pojištěnou osobu na základě dat z formuláře.
     * Zároveň provádí validaci vstupních dat a uchovává případné chyby do objektu BindingResult.
     *
     * @param insuranceRecordDTO objekt s daty z formuláře pro vytvoření pojištění
     * @param insuredPersonId ID pojištěné osoby, ke které se záznam vztahuje
     * @param bindingResult objekt pro zachycení výsledků validace vstupních dat
     */
    void  createInsuranceRecord(InsuranceRecordDTO insuranceRecordDTO, Long insuredPersonId, BindingResult bindingResult);


    /**
     * Vrátí detailní informace o pojištěné osobě na základě ID pojištění,
     * včetně stránkovaného seznamu jejích pojištění.
     *
     * @param insuranceId ID pojištění, na základě kterého se dohledá pojištěná osoba
     * @param page číslo stránky výsledků (indexováno od nuly)
     * @param size počet záznamů na stránku
     * @return objekt obsahující informace o pojištěné osobě a stránkovaná data pojištění
     */
    PagedInsuranceDTO getInsuredPersonDetail(Long insuranceId, int page, int size);

    /**
     *
     * @param insuranceRecordDTO
     */
    void edit(InsuranceRecordDTO insuranceRecordDTO);

    /**
     *
     * @param insuranceId
     * @return
     */
    InsuranceRecordDTO getById(Long insuranceId);

    /**
     *
     * @param insuranceId
     */
    void remove(long insuranceId);
}
