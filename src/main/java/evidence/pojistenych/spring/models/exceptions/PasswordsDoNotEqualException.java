package evidence.pojistenych.spring.models.exceptions;

/**
 * Výjimka, která se vyhodí v případě, že zadaná hesla nejsou stejná.
 * Dědí z RuntimeException, takže je to nečekaná (unchecked) výjimka.
 */
public class PasswordsDoNotEqualException extends RuntimeException{

    public PasswordsDoNotEqualException(String message) {
        super(message);
    }
}
