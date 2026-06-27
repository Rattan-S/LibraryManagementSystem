package ca.sheridancollege.rattan.services;

import ca.sheridancollege.rattan.database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private DatabaseAccess da;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ca.sheridancollege.rattan.beans.User user = da.findUserAccount(username);
        
        if (user == null) {
            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("User not found: " + username);
        }

        List<String> roleNameList = da.getRolesById(user.getUserid());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNameList != null) {
            for (String role : roleNameList) {
                grantList.add(new SimpleGrantedAuthority(role));
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getEncryptedPassword(), grantList);
    }
}