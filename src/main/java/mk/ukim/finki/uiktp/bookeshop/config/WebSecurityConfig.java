//package mk.ukim.finki.uiktp.bookeshop.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//
//    private final PasswordEncoder passwordEncoder;
//    private final CustomUsernameAuthenticationProvider authenticationProvider;
//
//    public WebSecurityConfig(PasswordEncoder passwordEncoder, CustomUsernameAuthenticationProvider authenticationProvider) {
//        this.passwordEncoder = passwordEncoder;
//        this.authenticationProvider = authenticationProvider;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.GET, "/api/shopping-cart/**").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/api/shopping-cart/**").authenticated()
//                        .requestMatchers(HttpMethod.DELETE, "/api/shopping-cart/**").authenticated()
//                        .requestMatchers(HttpMethod.PUT, "/api/shopping-cart/**").authenticated()
//
//                        .requestMatchers(HttpMethod.GET, "/api/users/**").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/api/users/add").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.PUT, "/api/users/edit/**").hasRole("ADMIN")
//                        .requestMatchers(HttpMethod.DELETE, "/api/users/delete/**").hasRole("ADMIN")
//
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .httpBasic(withDefaults());
//
//        return http.build();
//    }
//
//}
