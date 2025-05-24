package evidence.pojistenych.spring.data.entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * Třída reprezentuje uživatele aplikace v databázi.
 * Implementuje rozhraní UserDetails pro potřeby Spring Security.
 */
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId; // ID uživatele, automaticky generované

    @Column(nullable = false, unique = true)
    private String email; // Email uživatele, musí být unikátní

    @Column(nullable = false)
    private String password; // Heslo uživatele

    @Column(nullable = false)
    private boolean admin; // Určuje, jestli je uživatel admin (true) nebo ne (false)

    /**
     * Vrátí ID uživatele.
     * @return ID uživatele
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Nastaví ID uživatele.
     * @param userId ID uživatele
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * Vrátí email uživatele.
     * @return email uživatele
     */
    public String getEmail() {
        return email;
    }

    /**
     * Nastaví email uživatele.
     * @param email email uživatele
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vrátí heslo uživatele.
     * @return heslo uživatele
     */
    public String getPassword() {
        return password;
    }

    /**
     * Nastaví heslo uživatele.
     * @param password heslo uživatele
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Nastaví, jestli je uživatel admin.
     * @param admin true pro admina, jinak false
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Vrátí uživatelské jméno pro přihlášení.
     * V tomto případě je to email.
     * @return uživatelské jméno (email)
     */
    @Override
    public String getUsername(){
        return email;
    }

    /**
     * Vrátí role uživatele pro Spring Security.
     * Buď ROLE_ADMIN nebo ROLE_USER podle hodnoty admin.
     * @return seznam oprávnění uživatele
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + (admin ? "ADMIN" : "USER"));
        return List.of(authority);
    }

    /**
     * Kontrola, jestli není účet expirovaný.
     * Vždy vrací true, účet neexpiruje.
     * @return true
     */
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    /**
     * Kontrola, jestli není účet zamčený.
     * Vždy vrací true, účet není zamčený.
     * @return true
     */
    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    /**
     * Kontrola, jestli nejsou expirované přihlašovací údaje.
     * Vždy vrací true.
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    /**
     * Kontrola, jestli je účet povolený (aktivní).
     * Vždy vrací true.
     * @return true
     */
    @Override
    public boolean isEnabled(){
        return true;
    }






}
