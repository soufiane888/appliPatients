package com.example.patientsmvc;

import com.example.patientsmvc.entities.Patient;
import com.example.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
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
}