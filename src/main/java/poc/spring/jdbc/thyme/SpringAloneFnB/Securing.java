package poc.spring.jdbc.thyme.SpringAloneFnB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securing {

//    @Bean
//    public WebSecurityCustomizer share() {
//        return(web)->web.ignoring().requestMatchers("/login");
//    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity hp)throws Exception{
        hp.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
        return hp.build();
    }
}
