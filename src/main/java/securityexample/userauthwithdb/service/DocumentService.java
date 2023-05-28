package securityexample.userauthwithdb.service;

import org.springframework.stereotype.Service;

import securityexample.userauthwithdb.entities.Document;
import securityexample.userauthwithdb.entities.Privilege;
import securityexample.userauthwithdb.entities.UserData;
import securityexample.userauthwithdb.repositories.DocumentRepository;
import securityexample.userauthwithdb.repositories.PrivilegeRepository;
import securityexample.userauthwithdb.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    private PrivilegeRepository privilegeRepository;
    
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

        // Find all the documents from the user.
        List<Document> ownDocs = documentRepository
            .findUserDocuments(user.get().getUserId());

        // Find all documents that the user has access but is from others.
        List<Long> docIds = a.getAuthorities().stream().map(auth -> 
            Long.parseLong(auth.getAuthority().split(":")[1]))
            .collect(Collectors.toList());
        
        List<Document> docs = documentRepository.findAllById(docIds);
        return docs;
    }
}
