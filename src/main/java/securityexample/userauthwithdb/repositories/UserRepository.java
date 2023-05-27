package securityexample.userauthwithdb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import securityexample.userauthwithdb.entities.UserData;

public interface UserRepository extends JpaRepository<UserData, Long>{

    Optional<UserData> findUserDataByUsername(String username);
}
