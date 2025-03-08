package evidence.pojistenych.spring.models.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


public class InsuredPersonDTO {


    private long id;

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

    @NotNull(message = "Vyplňte PSČ")
    @Min( value = 10000, message = "PSČ musí být minámálně 10000")
    @Max(value = 90000, message = "PSČ musí být maximálně 90000")
    private int zipCode;

    @Valid
    private UserDTO userDTO = new UserDTO();  // Tento objekt obsahuje email


    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    // Getter a setter pro email (přímo v DTO, ale lze i z UserDTO)
    public String getEmail() {
        return userDTO != null ? userDTO.getEmail() : null;
    }

    public void setEmail(String email) {
        if (userDTO != null) {
            userDTO.setEmail(email);
        }
    }

    public long getInsuredPersonId() {
        return id;
    }

    public void setInsuredPersonId(long insuredPersonId) {
        this.id = insuredPersonId;
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
