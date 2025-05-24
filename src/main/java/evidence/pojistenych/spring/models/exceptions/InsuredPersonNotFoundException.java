package evidence.pojistenych.spring.models.exceptions;

/**
 * Výjimka, která se vyhodí, pokud pojištěnec s daným ID nebyl nalezen v databázi.
 */
public class InsuredPersonNotFoundException extends RuntimeException {

    /**
     * Vytvoří novou výjimku s chybovou zprávou, která obsahuje ID hledaného pojištěnce.
     *
     * @param id ID pojištěnce, který nebyl nalezen
     */
    public InsuredPersonNotFoundException(Long id) {
      super("Pojištěnec s ID " + id + " nenalezen");
    }
}
