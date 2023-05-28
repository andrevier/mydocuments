package securityexample.userauthwithdb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import securityexample.userauthwithdb.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{
    @Query(value = "SELECT * FROM document d WHERE d.user_id = :userId",
     nativeQuery = true)
    List<Document> findUserDocuments(Long userId);
}
