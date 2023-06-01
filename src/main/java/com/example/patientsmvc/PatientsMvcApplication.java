package com.example.patientsmvc;

import com.example.patientsmvc.entities.Patient;
import com.example.patientsmvc.repositories.PatientRepository;
import com.example.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
		return args -> {
			patientRepository.save(new Patient(null, "Mohamed", new Date(), false, 12));
			patientRepository.save(new Patient(null, "David", new Date(), true, 122));
			patientRepository.save(new Patient(null, "Mathilde", new Date(), false, 332));
			patientRepository.save(new Patient(null, "Marie", new Date(), true, 66));
			patientRepository.findAll().forEach(patient -> {
				System.out.println(patient.getNom());
			});
		};
	}
	//@Bean
	CommandLineRunner saveUsers(SecurityService securityService){
		return args -> {
			securityService.saveNewUser("mohamed","1234","1234");
			securityService.saveNewUser("noé","1234","1234");
			securityService.saveNewUser("Léa","1234","1234");

			securityService.saveNewRole("USER","");
			securityService.saveNewRole("ADMIN","");
			securityService.addRoleToUser("mohamed","USER");
			securityService.addRoleToUser("mohamed","ADMIN");
			securityService.addRoleToUser("noé","USER");
			securityService.addRoleToUser("léa","USER");

		};
	}

}