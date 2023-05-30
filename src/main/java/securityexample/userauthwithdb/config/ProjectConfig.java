package securityexample.userauthwithdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class ProjectConfig {
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests((authz) ->{
           authz.requestMatchers("/edit").permitAll();
           authz.requestMatchers("/update-document").permitAll();
           authz.anyRequest().authenticated();})
        .formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/home", true);})
        .logout((logout) -> logout.permitAll())
        .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
}
