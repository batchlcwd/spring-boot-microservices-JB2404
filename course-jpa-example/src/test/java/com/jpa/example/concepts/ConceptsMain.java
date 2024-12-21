package com.jpa.example.concepts;

import com.jpa.example.entity.Laptop;
import com.jpa.example.entity.User;
import com.jpa.example.repository.LaptopRepo;
import com.jpa.example.repository.UserRepository;
import com.jpa.example.service.UserService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest

public class ConceptsMain {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LaptopRepo laptopRepo;

    @Autowired
    private UserService userService;

    @Test
    public void testSaveOneToOne() {

        User user = userRepository.findById(1010).orElseThrow(() -> new RuntimeException("user not found"));

        //new laptop
        Laptop laptop = new Laptop();
        laptop.setModel("Dell Inspiration 1232");
        laptop.setAbout("This is latest from Dell");

//
//        user.setLaptop(laptop);
//        laptop.setUser(user);
//
//        laptopRepo.save(laptop);
//        System.out.println("laptop added");

    }

    @Test
    public void testGetOneToOne() {

//        User user = userRepository.findById(1010).orElseThrow(() -> new RuntimeException("user not found"));
//        System.out.println(user.getName());
//        Laptop laptop = user.getLaptop();
//        System.out.println(laptop.getId()+"\t"+laptop.getModel());
//        Laptop laptop = laptopRepo.findById(1).orElseThrow();
//        System.out.println(laptop.getModel());
//        //session : jpa ne close:
//        User user = laptop.getUser();
//        System.out.println(user.getName());

    }


    @Test

    public void saveOneToMany() {


        User user = userRepository.findById(1010).orElseThrow();

        user.setName("Vivek");

        Laptop laptop1 = new Laptop();
        laptop1.setModel("Macbook Air new new");
        laptop1.setAbout("This is first laptop of vivek");

        Laptop laptop2 = new Laptop();
        laptop2.setModel("Macbook Pro new");
        laptop2.setAbout("This is second laptop of vivek");


        laptop1.setUser(user);
        laptop2.setUser(user);


        System.out.println(user.getLaptops().size());
        Laptop laptop = user.getLaptops().removeFirst();
        laptop.setUser(null);

        user.getLaptops().add(laptop1);
        user.getLaptops().add(laptop2);


        userRepository.save(user);

        System.out.println(user.getLaptops().size());

        System.out.println("user updated:");


    }

    @Test
    public void testAuditing() {

        User user = userRepository.findById(10011).get();
        user.setName("Durgesh Change");
        userRepository.save(user);

    }


}
