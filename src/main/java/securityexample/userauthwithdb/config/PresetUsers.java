package securityexample.userauthwithdb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import securityexample.userauthwithdb.entities.Document;
import securityexample.userauthwithdb.entities.UserData;
import securityexample.userauthwithdb.repositories.DocumentRepository;
import securityexample.userauthwithdb.repositories.PrivilegeRepository;
import securityexample.userauthwithdb.repositories.UserRepository;

@Configuration
public class PresetUsers {
    @Bean
    CommandLineRunner preset(
        BCryptPasswordEncoder bcrypt,
        UserRepository userRepository,
        PrivilegeRepository privilegeRepository,
        DocumentRepository documentRepository) {
        return args -> {
            UserData user1 = userRepository.save(
                new UserData("mateus", bcrypt.encode("1234")));
            documentRepository.save(
                new Document("My first text.", user1));
        };
    }
}
