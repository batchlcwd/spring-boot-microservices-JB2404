package com.jpa.example;

import com.jpa.example.entity.User;
import com.jpa.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class CourseJpaExampleApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(CourseJpaExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		System.out.println("going to save user:");

//		User user = new User();
//		user.setUserId(123);
//		user.setName("Gaurav");
//		user.setAge(12);
//		user.setActive(true);
//		User savedUser = userRepository.save(user);
//		System.out.println(savedUser.getName());
//		System.out.println("user saved:");

//		List<User> all = userRepository.findAll();
//		all.forEach(user -> {
//			System.out.println(user.getName());
//		});

	}
}
