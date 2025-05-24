package evidence.pojistenych.spring.models.exceptions;

/**
 * Výjimka, která se vyhazuje, pokud se uživatel pokusí registrovat s emailem,
 * který už je v systému zaregistrovaný.
 */
public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String email) {
        super("Email '" + email + "' je již používán.");
    }
}
