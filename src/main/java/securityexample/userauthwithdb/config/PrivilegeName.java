package securityexample.userauthwithdb.config;

public class PrivilegeName {

    public static String setName(Long documentId) {
        return "DOCUMENT:" + documentId;
    }
    
}
