package securityexample.userauthwithdb.service;

import org.springframework.stereotype.Service;

import securityexample.userauthwithdb.dto.DocumentDto;
import securityexample.userauthwithdb.dto.DocumentRequest;
import securityexample.userauthwithdb.entities.Document;
import securityexample.userauthwithdb.entities.UserData;
import securityexample.userauthwithdb.repositories.DocumentRepository;
import securityexample.userauthwithdb.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private UserRepository userRepository;
    
    public List<DocumentDto> getDocuments() {
        // Find the principal user.
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();
        
        // Get the user object.
        Optional<UserData> user = userRepository
            .findUserDataByUsername(a.getName());
        if (user.isEmpty())  {
            return new ArrayList<DocumentDto>();
        }

        // Find all documents that the user has access but is from others.
        List<Long> docIds = a.getAuthorities().stream().map(auth -> 
            Long.parseLong(auth.getAuthority().split(":")[1]))
            .collect(Collectors.toList());
        
        List<Document> docs = documentRepository.findAllById(docIds);
        
        List<DocumentDto> docDtos = new ArrayList<>();

        for (Long id: docIds) {
            docDtos.add(this.documentRepository.findByIdReturnDto(id));
        }
        return docDtos;
    }

    public DocumentDto getDocumentById(Long documentId) {
        return this.documentRepository.findByIdReturnDto(documentId);
    }

    public HttpStatusCode updateDocument(DocumentRequest doc) {
        // UserData user = this.userRepository
        //     .findUserDataByUsername(auth.getName()).get();
        
        Document updateDocument = this.documentRepository
            .findById(doc.getDocumentId()).get();
        
        updateDocument.setTitle(doc.getTitle());
        updateDocument.setContent(doc.getContent());
        
        documentRepository.save(updateDocument);

        return HttpStatusCode.valueOf(200);
    }
}
