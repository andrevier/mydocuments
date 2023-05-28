package securityexample.userauthwithdb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import securityexample.userauthwithdb.entities.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long>{
    @Query(value = "SELECT * FROM privileges p WHERE p.user_id = :userId",
    nativeQuery = true)
    List<Privilege> findPrivilegesByUserId(Long userId);
}
