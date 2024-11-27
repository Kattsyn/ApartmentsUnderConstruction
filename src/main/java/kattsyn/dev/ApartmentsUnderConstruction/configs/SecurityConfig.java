package kattsyn.dev.ApartmentsUnderConstruction.configs;

import kattsyn.dev.ApartmentsUnderConstruction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)  // Отключаем CSRF для форм
                .cors(CorsConfigurer::disable)  // Отключаем CORS для форм
                .authorizeHttpRequests(authorize -> authorize
                        // Страница /secured доступна только для пользователей с ролью USER
                        .requestMatchers("/secured").hasRole("USER")

                        // Страница /manager доступна только для пользователей с ролью MANAGER
                        .requestMatchers("/manager").hasRole("MANAGER")

                        // Страница /admin доступна только для пользователей с ролью ADMIN
                        .requestMatchers("/admin").hasRole("ADMIN")

                        // Страница login доступна всем
                        .requestMatchers("/login", "/register").permitAll()

                        // Страница home доступна всем
                        .requestMatchers("/home").permitAll()

                        // Остальные запросы требуют аутентификации
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/login")
                        .failureUrl("/failureUrl")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/defaultSuccessUrl")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL для выхода
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());  // После выхода редиректим на страницу входа

        return http.build();
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
