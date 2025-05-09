package evidence.pojistenych.spring.models.dto;


import jakarta.validation.constraints.*;

import java.util.List;


public class InsuredPersonDTO {


    private Long id;

    @NotBlank(message = "Vyplňte křestní jméno")
    @Size(min = 3, message = "Vyplňte validní křestní jméno")
    private String firstName;

    @NotBlank(message = "Vyplňte příjmení")
    @Size(min = 3, message = "Vyplňte validní příjmení")
    private String lastName;

    @NotBlank(message = "Vyplňte email")
    @Email(message = "Email mus být validní")
    private String email;

    @Pattern(regexp = "^(\\+\\d{1,3}[ -]?)?\\d{1,4}([ -]?\\d{1,4}){2,3}$", message = "Vyplňte validní telefonní číslo")
    @NotBlank(message = "Vyplňte telefonní číslo")
    private String phoneNumber;

    @NotBlank(message = "Vyplňte ulici a číslo popisné")
    private String street;

    @Pattern(regexp = "^[A-Za-zÁáÉéÍíÓóÚúÝýČčĎďŇňŘřŠšŤťŽžŽśý]+([ '-][A-Za-zÁáÉéÍíÓóÚúÝýČčĎďŇňŘřŠšŤťŽžŽśý]+)*$", message = "Vyplňte validní město")
    @NotBlank(message = "Vyplňte město")
    private String city;

    @NotNull(message = "Vyplňte PSČ")
    @Min( value = 10000, message = "PSČ musí být validní 10000")
    @Max(value = 90000, message = "PSČ musí být validní90000")
    private int zipCode;



    private List<InsuranceRecordDTO> insuranceEntities;

    public List<InsuranceRecordDTO> getInsuranceEntities() {
        return insuranceEntities;
    }

    /**
     * setter pro nastavení ceizího klíče
     * @param insuranceEntities
     */
    public void setInsuranceEntities(List<InsuranceRecordDTO> insuranceEntities) {
        this.insuranceEntities = insuranceEntities;
    }

    /**
     * Getter pro id Pojištěné osoby
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter pro id Pojištěné osoby
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter Křestního jména
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter Křestního jména
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter příjméní
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter pro příjmení
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     *
     * @param zipCode
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
