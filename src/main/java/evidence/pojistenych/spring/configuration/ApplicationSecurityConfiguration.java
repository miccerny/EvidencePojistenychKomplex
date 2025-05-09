package evidence.pojistenych.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class ApplicationSecurityConfiguration {


    /**
     * Konfigurace bezpečnostního filtru pro aplikaci pomocí Spring Security.
     * *
     * Tato metoda definuje nastavení pro autorizaci požadavků, přihlašování a odhlašování uživatelů.
     * *
     * - Všechny požadavky jsou povoleny bez autentifikace.
     * - Nastavení pro formulářové přihlašování, s vlastní přihlašovací stránkou a zpracováním přihlášení.
     * - Po úspěšném přihlášení bude uživatel přesměrován na domovskou stránku ("/").
     * - Parametr pro uživatelské jméno je nastaven na "email".
     * - Odhlašování je povoleno prostřednictvím URL "/account/logout".
     *
     * @param httpSecurity Konfigurace bezpečnosti HTTP.
     * @return SecurityFilterChain Nastavený bezpečnostní filtr pro aplikaci.
     * @throws Exception Pokud dojde k chybám při konfiguraci bezpečnostních pravidel.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{

        return httpSecurity
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/", true)
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .and()
                .build();

    }

    /**
     * Tato metoda vytvoří a vrátí objekt, který slouží k šifrování a ověřování hesel.
     * Používáme třídu `BCryptPasswordEncoder` z knihovny Spring Security, která je oblíbená pro bezpečné uchovávání hesel.
     * *
     * - BCrypt je způsob, jakým se hesla šifrují, což znamená, že i když někdo získá šifrované heslo, nebude schopný ho snadno zjistit.
     * - Tento kód použijeme, když chceme ověřit heslo, které uživatel zadá při přihlášení, nebo když chceme uložit nové heslo.
     *
     * @return Vrátí instanci třídy `BCryptPasswordEncoder`, kterou budeme používat k práci s hesly.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
