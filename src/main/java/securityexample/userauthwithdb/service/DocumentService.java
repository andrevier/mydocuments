package securityexample.userauthwithdb.service;

import org.springframework.stereotype.Service;

import securityexample.userauthwithdb.entities.Document;
import securityexample.userauthwithdb.repositories.DocumentRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getDocuments() {
        return new ArrayList<Document>();
    }

}
