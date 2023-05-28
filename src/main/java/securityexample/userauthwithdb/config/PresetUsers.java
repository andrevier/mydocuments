package securityexample.userauthwithdb.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import securityexample.userauthwithdb.entities.Document;
import securityexample.userauthwithdb.entities.Privilege;
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
            
            Document doc1 = documentRepository.save(
                new Document("My first text.", user1));
            
            privilegeRepository.save(
                new Privilege(PrivilegeName.setName(doc1.getDocumentId()), user1));
            
            Document doc2 = documentRepository.save(
                new Document("This is mateus' content.", user1));
            
            privilegeRepository.save(
                new Privilege(PrivilegeName.setName(doc2.getDocumentId()), user1));
            
            // User 2.
            UserData user2 = userRepository.save(
                new UserData("john", bcrypt.encode("1234")));
            
            Document doc3 = documentRepository.save(
                new Document("This is john's content.", user2));
            
            privilegeRepository.save(
                new Privilege(PrivilegeName.setName(doc3.getDocumentId()), user2));
            
            // Giving access to the content from user 2 to the user 1.
            privilegeRepository.save(
                new Privilege(PrivilegeName.setName(doc3.getDocumentId()), user1));
            
        };
    }
}
