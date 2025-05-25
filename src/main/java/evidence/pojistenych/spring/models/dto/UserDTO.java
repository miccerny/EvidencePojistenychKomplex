package evidence.pojistenych.spring.models.dto;

import jakarta.validation.constraints.*;

/**
 * Třída slouží pro přenos údajů o uživateli při registraci nebo přihlášení.
 */
public class UserDTO {

    /**
     * Emailová adresa uživatele.
     * Musí být validní a nesmí být prázdná.
     */
    @Email(message = "Vyplňte validní email")
    @NotBlank(message = "Vyplňte email")
    private String email;

    /**
     * Uživatelské heslo.
     * Musí mít alespoň 6 znaků a nesmí být prázdné.
     */
    @NotBlank(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String password;

    /**
     * Potvrzení hesla.
     * Musí mít alespoň 6 znaků a nesmí být prázdné.
     */
    @NotBlank(message = "Vyplňte uživatelské heslo")
    @Size(min = 6, message = "Heslo musí mít alespoň 6 znaků")
    private String confirmPassword;

    /**
     * Vrací email uživatele.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Nastaví email uživatele.
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vrací heslo uživatele.
     * @return heslo
     */
    public String getPassword() {
        return password;
    }

    /**
     * Nastaví heslo uživatele.
     * @param password heslo
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Vrací potvrzené heslo.
     * @return potvrzené heslo
     */
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
