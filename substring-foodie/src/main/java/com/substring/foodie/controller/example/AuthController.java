package com.substring.foodie.controller.example;

import com.substring.foodie.playload.example.UserDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequestMapping("/auth")
public class AuthController {

    //login
    // logger declare::
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    //    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @GetMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, Object> data) {

        String test=null;
        test.length();//null pointer exception ko generate kiya hai

        logger.info("login request: {}", data);
        return data;
    }

    //signup:

    //    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @PostMapping("/signup")
    public String signup(
            @Valid @RequestBody UserDTO userDTO,
            @RequestHeader("User-Agent") String agent,
            @RequestHeader("Authorization") String token
    ) {

        //print on console:

        //more control: Logging framework: spring boot: [inbuilt]
//            -> console
//             -> file
//              -> change printing form
//              -> error
//                -> debug
//              -> trace
//             -> info
//        System.out.println(userData.getName());

        logger.info("user name : {}", userDTO.getName());
        logger.info("password : {} ", userDTO.getPassword());
        logger.info("email : {} ", userDTO.getEmail());
        logger.info("age : {} ", userDTO.getAge());
        logger.info("user agent : {}", agent);
        logger.info("token : {}", token);
        return "we got data";

    }


    //exception handling method: for this controller

//    @ExceptionHandler(NullPointerException.class)
//    public String handleNullPointerException(NullPointerException ex) {
//        logger.error(ex.getMessage());
//        ex.printStackTrace();
//        return ex.getMessage();
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        logger.error(ex.getMessage());
//        ex.printStackTrace();
//        return "your input data not valid !!";
//    }


}
