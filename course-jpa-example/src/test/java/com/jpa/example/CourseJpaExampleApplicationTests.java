package com.jpa.example;

import com.jpa.example.dto.UserType;
import com.jpa.example.entity.Address;
import com.jpa.example.entity.User;
import com.jpa.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
class CourseJpaExampleApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setUserId(101014);
        user.setName("Azaz Khan");
        user.setEmail("azaz@gmail.com");
        user.setType(UserType.STUDENT);
        user.setAge(33);
        user.setActive(true);
        Address address = new Address();
        address.setStreet("2352/23532 V Section12");
        address.setCountry("INDIA");
        address.setPinCode("220990");
        user.setAddress(address);
        User save = userService.save(user);
        System.out.println(save.getName());
    }

    @Test
    public void getUserTest() {
        User user = userService.get(1010);
        System.out.println(user.getName());
    }

    @Test
    public void testZoneExamples(){

        ZonedDateTime utcTime=ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println(utcTime);

        ZonedDateTime ist = utcTime.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
        System.out.println(ist);
    }

}
