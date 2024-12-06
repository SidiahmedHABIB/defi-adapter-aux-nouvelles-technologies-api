//package net.sda.myresumeapi.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private ClientRegistrationRepository clientRegistrationRepository;
//
//    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
//        this.clientRegistrationRepository = clientRegistrationRepository;
//    }
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(Customizer.withDefaults())
//                .authorizeHttpRequests(ar->ar.requestMatchers(
//                        "/webjars/**",
//                        "/swagger-resources",
//                        "/swagger-resources/**").permitAll())
//                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
//                //.headers(h->h.frameOptions(fo->fo.disable()))
//                //.csrf(csrf->csrf.ignoringRequestMatchers("/h2-console/**"))
//                .oauth2Login(Customizer.withDefaults())
//                .logout((logout) -> logout
//                        //.logoutSuccessHandler(oidcLogoutSuccessHandler())
//                        .logoutSuccessUrl("/").permitAll()
//                        .clearAuthentication(true)
//                        .deleteCookies("JSESSIONID"))
//                //.exceptionHandling(eh->eh.accessDeniedPage("/notAutorized"))
//                .build();
//    }
//}