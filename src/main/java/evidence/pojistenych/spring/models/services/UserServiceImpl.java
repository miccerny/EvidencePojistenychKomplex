package evidence.pojistenych.spring.models.services;

import evidence.pojistenych.spring.data.entities.UserEntity;
import evidence.pojistenych.spring.data.repository.UserRepository;
import evidence.pojistenych.spring.models.dto.UserDTO;
import evidence.pojistenych.spring.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Vytvoří nového uživatele na základě dat z DTO.
     * Nejprve ověří, zda se hesla shodují, a pokud ne, přidá chybu do `BindingResult` a vyvolá výjimku.
     * Poté vytvoří nového uživatele, nastaví jeho údaje a uloží do databáze.
     * Pokud již existuje uživatel s daným emailem, vyvolá `DataIntegrityViolationException`, která je zachycena
     * a přidá chybu týkající se duplicity emailu do `BindingResult`.
     *
     * @param userDTO DTO obsahující data pro vytvoření nového uživatele
     * @param isAdmin Určuje, zda je uživatel administrátor
     * @param bindingResult Výsledek validačních kontrol, kde se ukládají případné chyby
     */
    @Override
    public void create(UserDTO userDTO, boolean isAdmin, BindingResult bindingResult){

        validatePasswordMatch(userDTO, bindingResult);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setAdmin(isAdmin);

        checkEmailUnique(userDTO,bindingResult);

        userRepository.save(userEntity);
    }

    /**
     * Načte uživatele podle uživatelského jména (v tomto případě emailu) a vrátí jeho detaily.
     * Pokud uživatel s daným uživatelským jménem (email) neexistuje, vyvolá výjimku `UsernameNotFoundException`.
     *
     * @param username Uživatelské jméno (email) uživatele, jehož detaily mají být načteny
     * @return Uživatelské detaily (`UserDetails`) pro daného uživatele
     * @throws UsernameNotFoundException Pokud uživatel s daným uživatelským jménem (email) nebyl nalezen
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByEmail(username);
    }

    /**
     * Ověří, zda se heslo a potvrzení hesla shodují. Pokud se neshodují, přidá chybu do `BindingResult`
     * a vyvolá výjimku `PasswordsDoNotEqualException`.
     *
     * @param userDTO DTO obsahující heslo a potvrzení hesla pro validaci
     * @param bindingResult Objekt pro shromažďování validačních chyb, které mohou být přidány v případě neplatného vstupu
     * @throws PasswordsDoNotEqualException Pokud se heslo a potvrzení hesla neshodují
     */
    private void validatePasswordMatch(UserDTO userDTO, BindingResult bindingResult) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error", "Hesla se neshodují.");
            bindingResult.rejectValue("confirmPassword", "error", "Hesla se neshodují.");
            throw new PasswordsDoNotEqualException();
        }
    }

    /**
     * Zkontroluje, zda je email uživatele unikátní. Pokud již existuje uživatel s daným emailem,
     * přidá chybu do `BindingResult` a informuje, že email je již používán.
     *
     * @param userDTO DTO obsahující email, který má být zkontrolován
     * @param bindingResult Objekt pro shromažďování validačních chyb, které mohou být přidány v případě nevalidního emailu
     */
    private void checkEmailUnique(UserDTO userDTO, BindingResult bindingResult) {
        try {
            userRepository.save(new UserEntity());
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("email", "error", "Email je již používán.");
        }
    }

    /**
     * Načte uživatele na základě jeho emailu. Pokud uživatel s daným emailem neexistuje,
     * vyvolá výjimku `UsernameNotFoundException`.
     *
     * @param email Email uživatele, jehož detaily mají být načteny
     * @return Uživatelské detaily (`UserDetails`) pro daného uživatele
     * @throws UsernameNotFoundException Pokud uživatel s daným emailem nebyl nalezen
     */
    private UserDetails getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + email + " nenalezen"));
    }
}
