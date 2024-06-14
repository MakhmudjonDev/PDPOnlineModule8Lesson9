//package app.security_config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity(
//        securedEnabled = true,
//        jsr250Enabled = true
//)
//public class SecurityConfigurer {
//    protected static final String[] WHITE_LIST = {
//            "/todos",
//            "/auth/login",
//            "/auth/register",
//            "/templates/"
//    };
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        http.authorizeHttpRequests()
//                .requestMatchers(WHITE_LIST).permitAll()
//                .anyRequest()
//                .authenticated();
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}
