package evidence.pojistenych.spring.models.exceptions;

import java.time.LocalDate;

/**
 * Výjimka, která se vyhodí, pokud není nalezeno pojištění podle zadaného ID.
 */
public class InsuranceNotFoundException extends RuntimeException{

    /**
     * Vytvoří novou výjimku s chybovou zprávou, která obsahuje ID pojištění.
     *
     * @param id ID pojištění, které nebylo nalezeno
     */
    public InsuranceNotFoundException(Long id){
        super("Pojištění s ID " + id + " nenalezeno");
    }

}
