package securityexample.userauthwithdb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import securityexample.userauthwithdb.config.CustomUserDetails;
import securityexample.userauthwithdb.entities.Privilege;
import securityexample.userauthwithdb.entities.UserData;
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

    public static void addAuthority(
            Authentication auth, Privilege privilege) {
        // Add the new authority in the privilege object to the authorities
        // of the security context.
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>(
            auth.getAuthorities());
        
        updatedAuthorities.add(
            new SimpleGrantedAuthority(privilege.getPrivilegeName()));

        Authentication newAuth = new UsernamePasswordAuthenticationToken(
            auth.getPrincipal(),
            auth.getCredentials(),
            updatedAuthorities);
        
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public static void deleteAuthority(
        Authentication auth, Privilege privilege) {
        // Get the list of authorities from the security context.
        List<GrantedAuthority> currentAuthorities = new ArrayList<>(
            auth.getAuthorities());
        
        // Filter the list with privileges not equal to the privilege 
        // to be deleted.
        Predicate<GrantedAuthority> isNotEqual = authority -> !authority
            .getAuthority().equals(privilege.getPrivilegeName());
        
        List<GrantedAuthority> filteredAuthorities =  currentAuthorities
            .stream()
            .filter(isNotEqual)
            .collect(Collectors.toList());

        // Update the security context.
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
            auth.getPrincipal(),
            auth.getCredentials(),
            filteredAuthorities);
        
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
    
    
}
