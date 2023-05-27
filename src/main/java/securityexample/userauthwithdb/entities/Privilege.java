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
@Table(name = "privileges")
public class Privilege {
    @Id
    @SequenceGenerator(
        name = "privilege_sequence",
        sequenceName = "privilege_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "privilege_sequence"
    )
    @Column(name = "privilege_id", updatable = false)
    private Long privilegeId;

    @Column(name = "privilege_name", nullable = false)
    private String privilegeName;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserData user;

    public Privilege() {
    }

    public Privilege(String privilegeName, UserData user) {
        this.privilegeName = privilegeName;
        this.user = user;
    }

    public Long getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Long privilegeId) {
        this.privilegeId = privilegeId;
    }

    public String getPrivilegeName() {
        return privilegeName;
    }

    public void setPrivilegeName(String privilegeName) {
        this.privilegeName = privilegeName;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((privilegeId == null) ? 0 : privilegeId.hashCode());
        result = prime * result + ((privilegeName == null) ? 0 : privilegeName.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
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
        Privilege other = (Privilege) obj;
        if (privilegeId == null) {
            if (other.privilegeId != null)
                return false;
        } else if (!privilegeId.equals(other.privilegeId))
            return false;
        if (privilegeName == null) {
            if (other.privilegeName != null)
                return false;
        } else if (!privilegeName.equals(other.privilegeName))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Privilege [privilegeId=" + privilegeId 
            + ", privilegeName=" + privilegeName 
            + ", user=" + user + "]";
    }

}
