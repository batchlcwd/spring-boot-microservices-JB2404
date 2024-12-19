package com.jpa.example.concepts;

import com.jpa.example.entity.Laptop;
import com.jpa.example.entity.User;
import com.jpa.example.repository.LaptopRepo;
import com.jpa.example.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ConceptsMain {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LaptopRepo laptopRepo;

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

        user.setName("Vivek.");

        Laptop laptop1 = new Laptop();
        laptop1.setModel("Macbook Air");
        laptop1.setAbout("This is first laptop of vivek");

        Laptop laptop2 = new Laptop();
        laptop2.setModel("Macbook Pro");
        laptop2.setAbout("This is second laptop of vivek");


        user.getLaptops().add(laptop1);
        user.getLaptops().add(laptop2);

        laptop1.setUser(user);
        laptop2.setUser(user);


        userRepository.save(user);

        System.out.println("user updated:");


    }


}
