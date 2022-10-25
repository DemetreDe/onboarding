package att.onboarding;

import att.onboarding.model.AuthorizationLevel;
import att.onboarding.model.Employee;
import att.onboarding.service.AdminService;
import att.onboarding.utilities.EmailSender;
import att.onboarding.utilities.Utilities;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnboardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	Utilities utilities(){return new Utilities();}

	@Bean
	EmailSender emailSender(){return new EmailSender();}

	@Bean
	CommandLineRunner run(AdminService adminService){
		return args -> {
//			adminService.deleteEmployee(10L);
//			adminService.saveEmployee(new Employee(null, "Demetre", "De Alwis", "12345678", "demetre@gmail.com", null, null, null, "123", null,null,null,null,null ));
//			adminService.makeAdmin("demetre@gmail.com");

// 			adminService.saveAuthorizationLevel(new AuthorizationLevel(null, "ROLE_USER"));
//			adminService.saveAuthorizationLevel(new AuthorizationLevel(null, "ROLE_HR"));
//			adminService.saveAuthorizationLevel(new AuthorizationLevel(null, "ROLE_ADMIN"));
		};
	}
}