package securityexample.userauthwithdb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import securityexample.userauthwithdb.dto.DocumentDto;
import securityexample.userauthwithdb.entities.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{
    @Query(value = "SELECT d.document_id as documentId,"
    + " d.title as title, d.content as content, u.username as username"
    + " FROM document d JOIN user_data u ON u.user_id = d.user_id"
    + " WHERE d.user_id = :userId", nativeQuery = true)
    List<DocumentDto> findUserDocuments(Long userId);

    @Query(value = "SELECT d.document_id as documentId,"
    + " d.title as title, d.content as content, u.username as username"
    + " FROM document d JOIN user_data u ON u.user_id = d.user_id"
    + " WHERE d.document_id = :documentId", nativeQuery = true)
    DocumentDto findByIdReturnDto(Long documentId);
}
