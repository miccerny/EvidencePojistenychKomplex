package evidence.pojistenych.spring.data.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.util.List;

/**
 * Třída reprezentuje entitu pojištěné osoby v databázi.
 * /
 * Je mapována na tabulku "insured_person_entity".
 */
@Entity
@Table(name = "insured_person_entity")
public class InsuredPersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; // ID pojištěné osoby, automaticky generované

    @Column(nullable = false)
    private String firstName; // Jméno pojištěné osoby

    @Column(nullable = false)
    private String lastName; // Příjmení pojištěné osoby

    @Column(nullable = false)
    @Email
    private String email; // Email pojištěné osoby (musí být ve formátu emailu)

    @Column(nullable = false)
    private String phoneNumber;  // Telefonní číslo pojištěné osoby

    @Column(nullable = false)
    private String street; // Ulice a číslo pospisné pojištěné osoby

    @Column(nullable = false)
    private String city; // Město bydliště pojištěné osoby

    @Column(nullable = false)
    private int zipCode;  // PSČ bydliště pojištěné osoby

    @OneToMany(mappedBy = "insuredPerson", cascade = CascadeType.MERGE)
    private List<InsuranceEntity> insuranceEntities; // Seznam pojištění této osoby

    /**
     * Vrátí seznam pojištění pojištěné osoby.
     * @return seznam pojištění
     */
    public List<InsuranceEntity> getInsuranceEntities() {
        return insuranceEntities;
    }

    /**
     * Nastaví seznam pojištění pojištěné osoby.
     * @param insuranceEntities seznam pojištění
     */
    public void setInsuranceEntities(List<InsuranceEntity> insuranceEntities) {
        this.insuranceEntities = insuranceEntities;
    }

    /**
     * Vrátí ID pojištěné osoby.
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
     * Vrátí jméno pojištěné osoby.
     * @return jméno
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Nastaví jméno pojištěné osoby.
     * @param firstName jméno
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Vrátí příjmení pojištěné osoby.
     * @return příjmení
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Nastaví příjmení pojištěné osoby.
     * @param lastName příjmení
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Vrátí email pojištěné osoby.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Nastaví email pojištěné osoby.
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vrátí telefonní číslo pojištěné osoby.
     * @return telefonní číslo
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Nastaví telefonní číslo pojištěné osoby.
     * @param phoneNumber telefonní číslo
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Vrátí ulici bydliště pojištěné osoby.
     * @return ulice
     */
    public String getStreet() {
        return street;
    }

    /**
     * Nastaví ulici bydliště pojištěné osoby.
     * @param street ulice
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Vrátí město bydliště pojištěné osoby.
     * @return město
     */
    public String getCity() {
        return city;
    }

    /**
     * Nastaví město bydliště pojištěné osoby.
     * @param city město
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Vrátí PSČ bydliště pojištěné osoby.
     * @return PSČ
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * Nastaví PSČ bydliště pojištěné osoby.
     * @param zipCode PSČ
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }
}