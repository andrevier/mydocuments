package securityexample.userauthwithdb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "document")
public class Document {
    @Id
    @SequenceGenerator(
        name = "document_sequence",
        sequenceName = "document_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "document_sequence"
    )
    @Column(name = "document_id", updatable = false)
    private Long documentId;

    @Column(nullable = false)
    private String title;

    private String content;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData user;

    public Document() {
    }

    public Document(String title, UserData user) {
        this.title = title;
        this.user = user;
        this.content = "";
    }

    public Long getDocumentId() {
        return documentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public String getTitle() {
        return title;
    }

    public UserData getUser() {
        return user;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUser(UserData user) {
        this.user = user;
    }
}
