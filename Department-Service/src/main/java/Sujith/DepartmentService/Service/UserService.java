package Sujith.DepartmentService.Service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class UserService implements UserDetailsService
{

    @Getter@Setter
    private String username;
    @Getter@Setter
    private String password;
    @Getter@Setter
    private Date expirationDate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Logic to get the user from db


        return new User("admin","admin",new ArrayList<>());
    }
}
