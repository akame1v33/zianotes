package com.app;

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
public class App implements CommandLineRunner {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	public static void main(String args[]) {
		SpringApplication.run(App.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Student student = studentRepository.findById("7").get();
		System.out.println(student.toString());
		studentRepository.delete(student);
		
//		Student student = new Student();
//		
//		student.setName("ERROLITO");
//		student.setCreatedBy("ADMIN");
//		
//		Address address = new Address();
//		address.setStudent(student);
//		address.setCreatedBy("ADMIN");
//		address.setAddress("MALOLOS BULACAN");
//		
//		student.setAddress(address);
//		
		
//		addressRepository.save(address);
		
//		studentRepository.save(student);
	}

}
