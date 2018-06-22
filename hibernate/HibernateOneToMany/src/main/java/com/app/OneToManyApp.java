package com.app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.model.Address;
import com.model.Student;
import com.repository.AddressRepository;
import com.repository.StudentRepository;


@SpringBootApplication
@EnableJpaRepositories("com.repository")
@EntityScan("com.model")
public class OneToManyApp implements CommandLineRunner {

	@Autowired
	StudentRepository animeRepository;
	
	@Autowired
	AddressRepository addressRepository;

	
	public static void main(String args[]) {
		SpringApplication.run(OneToManyApp.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Address address = new Address();
		address.setAddress("Guadalupe Makati City");
//		
		List<Student> students = new ArrayList<Student>();
//		students.add( new Student(address,"Yukhira") );
//		students.add( new Student(address,"Akiri") );
//		students.add( new Student(address,"Megumi") );
		address.getStudents().add(new Student("I dont wanna another"));
		address.getStudents().add(new Student("Asta"));
		address.getStudents().add(new Student("Yuno"));
//		addressRepository.save(address);
//		animeRepository.saveAll(students); UNIDIRECTIONAL
//		animeRepository.save(new Student("Kirito")); BIDIRECTIONAL
		
		addressRepository.save(address); 
	}
	

}
