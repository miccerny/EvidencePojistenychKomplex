package evidence.pojistenych.spring.models.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class InsuredPersonDTO extends UserDTO{


    private long insuredPersonId;

    @NotBlank(message = "Vyplňte křestní jméno")
    @Size(min = 3, message = "Vyplňte validní křestní jméno")
    private String firstName;

    @NotBlank(message = "Vyplňte příjmení")
    @Size(min = 3, message = "Vyplňte validní příjmení")
    private String lastName;

    @NotBlank(message = "Vyplňte telefonní číslo")
    private String phoneNumber;

    @NotBlank(message = "Vyplňte ulici a číslo popisné")
    private String street;

    @NotBlank(message = "Vyplňte město")
    private String city;

    @NotBlank(message = "Vyplňte PSČ")
    @Size(min = 6, max = 6, message = "Vyplňte validní PSČ")
    private int zipCode;

    public long getInsuredPersonId() {
        return insuredPersonId;
    }

    public void setInsuredPersonId(long insuredPersonId) {
        this.insuredPersonId = insuredPersonId;
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
