package evidence.pojistenych.spring.models.dto;


import jakarta.validation.Valid;
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


    @NotBlank(message = "Vyplňte telefonní číslo")
    private String phoneNumber;

    @NotBlank(message = "Vyplňte ulici a číslo popisné")
    private String street;

    @NotBlank(message = "Vyplňte město")
    private String city;

    @NotNull(message = "Vyplňte PSČ")
    @Min( value = 10000, message = "PSČ musí být minámálně 10000")
    @Max(value = 90000, message = "PSČ musí být maximálně 90000")
    private int zipCode;



    private List<InsuranceRecordDTO> insuranceEntities;

    public List<InsuranceRecordDTO> getInsuranceEntities() {
        return insuranceEntities;
    }

    public void setInsuranceEntities(List<InsuranceRecordDTO> insuranceEntities) {
        this.insuranceEntities = insuranceEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}
