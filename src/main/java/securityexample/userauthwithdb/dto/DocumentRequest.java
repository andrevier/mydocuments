package securityexample.userauthwithdb.dto;

public class DocumentRequest implements DocumentDto {
    private Long documentId;
    private String title;
    private String content;
    private String username;
    
    public DocumentRequest() {
    }

    public DocumentRequest(
        Long documentId, 
        String title, 
        String content, 
        String username) {
        this.documentId = documentId;
        this.title = title;
        this.content = content;
        this.username = username;
    }

    @Override
    public Long getDocumentId() {
        return documentId;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((documentId == null) ? 0 : documentId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DocumentRequest other = (DocumentRequest) obj;
        if (documentId == null) {
            if (other.documentId != null)
                return false;
        } else if (!documentId.equals(other.documentId))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "DocumentRequest [documentId=" + documentId + ", title=" + title + ", content=" + content + ", username="
                + username + "]";
    }
    
}
