package securityexample.userauthwithdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import securityexample.userauthwithdb.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{
    
}
