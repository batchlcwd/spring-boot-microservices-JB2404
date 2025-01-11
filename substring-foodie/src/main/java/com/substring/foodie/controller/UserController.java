package com.substring.foodie.controller;

import com.substring.foodie.dto.UserDto;
import com.substring.foodie.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //create user

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
        UserDto userDtoResult = userService.saveUser(userDto);
//        return new ResponseEntity<>(userDtoResult, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoResult);
    }

    // get all users
    @GetMapping
    public ResponseEntity<Page<UserDto>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0")
            int page, @RequestParam(value = "size", required = false, defaultValue = "10")
            int size, @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "desc") String sortDir

    ) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return ResponseEntity.ok(userService.getAll(pageable));
    }


    //get user by id

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findById(@PathVariable("userId") String id) {
        return ResponseEntity.ok(userService.getUserById(id));

    }

    //
//    @RequestMapping("/")
//    //@ResponseBody
//    public String testing() {
//        System.out.println("user getting");
//        String template = "this is template";
//        return template;
//    }
//
//    @RequestMapping("/player-list")
//    public List<String> players() {
//
//
//        String template = null;
//        template.length();
//
//
//        List<String> list = new ArrayList<>();
//        list.add("Sachin");
//        list.add("MS Dhoni");
//        list.add("Virat");
//        list.add("Rahul");
//        list.add("Dravid");
//        list.add("Rishabh Pant");
//        list.add("Rohit");
//        return list;
//
//    }
//
//    //
//    @RequestMapping("/get-user")
//    public User getUser() {
//
//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setName("Sachin");
//        user.setEmail("sachin@gmail.com");
//        user.setPassword("123456");
//        user.setAddress("5/2354 Section 12 noida india");
//        user.setAvailable(true);
//
//        return user;
//    }


}
