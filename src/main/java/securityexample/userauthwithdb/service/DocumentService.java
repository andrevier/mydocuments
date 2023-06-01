package securityexample.userauthwithdb.service;

import org.springframework.stereotype.Service;

import securityexample.userauthwithdb.config.PrivilegeName;
import securityexample.userauthwithdb.dto.DocumentDto;
import securityexample.userauthwithdb.dto.DocumentRequest;
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

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    
    public List<DocumentDto> getDocuments() {
        // Find the principal user.
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        
        // Find all documents that the user has access but is from others.
        List<Long> docIds = a.getAuthorities().stream().map(auth -> 
            Long.parseLong(auth.getAuthority().split(":")[1]))
            .collect(Collectors.toList());
        
        List<DocumentDto> docDtos = new ArrayList<>();

        for (Long id: docIds) {
            docDtos.add(this.documentRepository.findByIdReturnDto(id));
        }
        return docDtos;
    }

    public DocumentDto getDocumentById(Long documentId) {
        return this.documentRepository.findByIdReturnDto(documentId);
    }

    public void updateDocument(DocumentRequest doc) {
        Optional<Document> updateDocument = Optional.ofNullable(
            this.documentRepository.findById(doc.getDocumentId()))
            .orElseThrow(() -> new UsernameNotFoundException(
                "Document id cannot be found. Object returned null"));
        
        updateDocument.get().setTitle(doc.getTitle());
        
        updateDocument.get().setContent(doc.getContent());
        
        this.documentRepository.save(updateDocument.get());

    }

    public void createDocument(String username, Authentication auth) {
        // Create a document for the user in username.
        Optional<UserData> user = Optional.ofNullable(
            this.userRepository.findUserDataByUsername(username))
            .orElseThrow(() -> new UsernameNotFoundException(username, null));
        
        Document newDoc = this.documentRepository.save(
            new Document("new note", user.get()));

        Privilege newPrivilege = this.privilegeRepository.save(
            new Privilege(
                PrivilegeName.setName(newDoc.getDocumentId()), user.get()));

        // After the creation of the document, the user's authorities must be
        // updated.
        JpaUserDetailsService.addAuthority(auth, newPrivilege);
    }

    public void deleteDocument(Authentication auth, Long documentId) {
        // Delete the document in the repository by its id.
        this.documentRepository.deleteById(documentId);

        // Then, delete all privileges for the document.
        List<Privilege> privileges = this.privilegeRepository
            .findAllWithName(PrivilegeName.setName(documentId));       

        for (Privilege privilege: privileges) {
            this.privilegeRepository.deleteById(privilege.getPrivilegeId());
            
            // Update the user's authorities in the security context.
            JpaUserDetailsService.deleteAuthority(auth, privilege);
        }        
    }

}