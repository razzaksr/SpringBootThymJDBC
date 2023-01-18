package poc.spring.jdbc.thyme.SpringAloneFnB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepos implements UserDetailsService {

    @Autowired
    JdbcTemplate temp;

    public String newUsers(Users users){
        temp.update("insert into users(username,password,contact) values(?,?,?)",new Object[]{users.getUsername(),users.getPassword(),users.getContact()});
        return users.getUsername()+" has signedUp";
    }

    public Users findByUsername(String username){
        Users users=temp.queryForObject("select * from users where username=?",new Object[]{username},new BeanPropertyRowMapper<Users>(Users.class));
        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u=findByUsername(username);
        if(u==null){
            throw new UsernameNotFoundException(username);
        }
        return u;
    }
}
