package securityexample.userauthwithdb.service;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import securityexample.userauthwithdb.entities.UserData;
import securityexample.userauthwithdb.model.CustomUserDetails;
import securityexample.userauthwithdb.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Declares a supplier to create exception instances.
        Supplier<UsernameNotFoundException> s = 
            () -> new UsernameNotFoundException(
                "The username is not found.");
        
        // Returns an Optional instance containing the user or an empty
        // Optional if the user does not exist. If the Optional instance 
        // is empty, throws an exception created by the defined Supplier
        // otherwise, it returns the User instance.
        UserData u = userRepository
            .findUserDataByUsername(username).orElseThrow(s);
        
        return new CustomUserDetails(u);
        
    }
    
}
