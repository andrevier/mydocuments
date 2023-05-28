package securityexample.userauthwithdb.service;

import org.springframework.stereotype.Service;

import securityexample.userauthwithdb.entities.Document;
import securityexample.userauthwithdb.entities.UserData;
import securityexample.userauthwithdb.repositories.DocumentRepository;
import securityexample.userauthwithdb.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;
    
    public List<Document> getDocuments() {
        // Find the principal user.
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        
        // Get the user object.
        Optional<UserData> user = userRepository
            .findUserDataByUsername(a.getName());
        if (user.isEmpty())  {
            return new ArrayList<Document>();
        }

        return documentRepository.findUserDocuments(user.get().getUserId());
    }

}
