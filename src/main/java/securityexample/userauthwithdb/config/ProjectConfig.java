package securityexample.userauthwithdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
           // authz.requestMatchers(HttpMethod.GET, "/css/**").permitAll();//("/css/**").permitAll();
           //authz.requestMatchers("/signin").permitAll();
           authz.anyRequest().authenticated();})
        .formLogin(form -> {
            form.loginPage("/login").permitAll();
            form.defaultSuccessUrl("/home", true);})
        .logout((logout) -> logout.permitAll())
        .httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
}
