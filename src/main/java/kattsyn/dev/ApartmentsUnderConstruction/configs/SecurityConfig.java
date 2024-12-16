package kattsyn.dev.ApartmentsUnderConstruction.configs;

import kattsyn.dev.ApartmentsUnderConstruction.exceptions.AuthFailureHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthFailureHandler authenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                //.csrf(CsrfConfigurer::disable)  // Отключаем CSRF для форм
                //.cors(CorsConfigurer::disable)  // Отключаем CORS для форм
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user").hasRole("USER")

                        .requestMatchers("/manager").hasRole("MANAGER")

                        .requestMatchers("/users/").hasRole("ADMIN")

                        .requestMatchers("/secured").authenticated()

                        .anyRequest().permitAll())
                .formLogin(formLogin -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        //.failureUrl("/login?error")
                        .failureHandler(authenticationFailureHandler)
                        .defaultSuccessUrl("/apartments/", true)
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
