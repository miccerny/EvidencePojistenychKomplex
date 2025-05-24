package evidence.pojistenych.spring.models.exceptions;

/**
 * Výjimka, která se vyhodí, pokud není nalezena pojištěná osoba podle zadaného ID.
 */
public class InsuredNotFoundExcption extends RuntimeException{

    public InsuredNotFoundExcption(Long id){
        super("Pojištěnec s ID " + id + " nenalezen");
    }
}
