package securityexample.userauthwithdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import securityexample.userauthwithdb.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
    
}
