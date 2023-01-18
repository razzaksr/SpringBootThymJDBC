package poc.spring.jdbc.thyme.SpringAloneFnB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securing {

    @Autowired
    UserRepos srv;

    AuthenticationManager manager;

//    @Bean
//    public WebSecurityCustomizer share() {
//        return(web)->web.ignoring().requestMatchers("/login");
//    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails det1= User.withUsername("rasheedha").password(passwordEncoder().encode("rajiya")).roles("ADMIN").build();
        return new InMemoryUserDetailsManager(det1);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity hp)throws Exception{
        hp.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();

        AuthenticationManagerBuilder builder=hp.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(srv).passwordEncoder(passwordEncoder());
        manager=builder.build();
        hp.authenticationManager(manager);


        return hp.build();
    }
}
