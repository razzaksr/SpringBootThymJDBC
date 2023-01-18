package poc.spring.jdbc.thyme.SpringAloneFnB;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringAloneFnBApplication {

//	@Autowired
//	UserRepos repo;
//
//	@Autowired
//	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringAloneFnBApplication.class, args);
	}

//	@PostConstruct
//	public void hai(){
//		Users u=new Users("sheik", encoder.encode("sheik"), 6545676534L);
//		System.out.println(repo.newUsers(u));
//	}

}

