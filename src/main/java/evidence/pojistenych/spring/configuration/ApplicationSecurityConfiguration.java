package evidence.pojistenych.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ApplicationSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws  Exception{

        return httpSecurity.authorizeHttpRequests()
                .requestMatchers(
                        "/projects/createRecord", "/projects/editRecord/**", "/projects/delete/**"
                )
                .authenticated()
                .requestMatchers(
                        "/styles/**", "/images/**", "/scripts/**", "/fonts/**",
                        "/projects/evidencePojistencu", "/", "/dovednosti", "/reference", "/contact",
                        "/account/register", "/projects/evidencePopjistencu/**", "/index"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/account/login")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/evidencePojistencu", true)
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/account/logout")
                .and()
                .build();
    }
}
