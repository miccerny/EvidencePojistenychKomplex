package evidence.pojistenych.spring.models.dto;


import jakarta.validation.constraints.*;
import java.util.List;

/**
 * Třída představuje data o pojištěné osobě.
 */
public class InsuredPersonDTO {

    private Long id;

    /**
     * Křestní jméno pojištěné osoby.
     */
    @NotBlank(message = "Vyplňte křestní jméno")
    @Size(min = 3, message = "Vyplňte validní křestní jméno")
    private String firstName;

    /**
     * Příjmení pojištěné osoby.
     */
    @NotBlank(message = "Vyplňte příjmení")
    @Size(min = 3, message = "Vyplňte validní příjmení")
    private String lastName;

    /**
     * Emailová adresa pojištěné osoby.
     */
    @NotBlank(message = "Vyplňte email")
    @Email(message = "Email mus být validní")
    private String email;

    /**
     * Telefonní číslo pojištěné osoby.
     */
    @Pattern(regexp = "^(\\+\\d{1,3}[ -]?)?\\d{1,4}([ -]?\\d{1,4}){2,3}$", message = "Vyplňte validní telefonní číslo")
    @NotBlank(message = "Vyplňte telefonní číslo")
    private String phoneNumber;

    /**
     * Ulice a číslo popisné bydliště pojištěné osoby.
     */
    @NotBlank(message = "Vyplňte ulici a číslo popisné")
    private String street;

    /**
     * Město bydliště pojištěné osoby.
     */
    @Pattern(regexp = "^[A-Za-zÁáÉéÍíÓóÚúÝýČčĎďŇňŘřŠšŤťŽžŽśý]+([ '-][A-Za-zÁáÉéÍíÓóÚúÝýČčĎďŇňŘřŠšŤťŽžŽśý]+)*$", message = "Vyplňte validní město")
    @NotBlank(message = "Vyplňte město")
    private String city;

    /**
     * PSČ pojištěné osoby.
     */
    @NotNull(message = "Vyplňte PSČ")
    @Min( value = 10000, message = "PSČ musí být validní 10000")
    @Max(value = 90000, message = "PSČ musí být validní90000")
    private int zipCode;

    /**
     * Seznam pojištění spojených s osobou.
     */
    private List<InsuranceRecordDTO> insuranceEntities;

    /**
     * Vrací seznam pojištění osoby.
     * @return seznam pojištění
     */
    public List<InsuranceRecordDTO> getInsuranceEntities() {
        return insuranceEntities;
    }

    /**
     * Nastaví seznam pojištění osoby.
     * @param insuranceEntities seznam pojištění
     */
    public void setInsuranceEntities(List<InsuranceRecordDTO> insuranceEntities) {
        this.insuranceEntities = insuranceEntities;
    }

    /**
     * Vrací ID pojištěné osoby.
     * @return ID osoby
     */
    public Long getId() {
        return id;
    }

    /**
     * Nastaví ID pojištěné osoby.
     * @param id ID osoby
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Vrací křestní jméno osoby.
     * @return křestní jméno
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Nastaví křestní jméno osoby.
     * @param firstName křestní jméno
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Vrací příjmení osoby.
     * @return příjmení
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Nastaví příjmení osoby.
     * @param lastName příjmení
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Vrací email osoby.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Nastaví email osoby.
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vrací telefonní číslo osoby.
     * @return telefonní číslo
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Nastaví telefonní číslo osoby.
     * @param phoneNumber telefonní číslo
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Vrací ulici a číslo popisné.
     * @return ulice a číslo
     */
    public String getStreet() {
        return street;
    }

    /**
     * Nastaví ulici a číslo popisné.
     * @param street ulice a číslo
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Vrací město.
     * @return město
     */
    public String getCity() {
        return city;
    }

    /**
     * Nastaví město.
     * @param city město
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Vrací PSČ.
     * @return PSČ
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Nastaví PSČ.
     * @param zipCode PSČ
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
