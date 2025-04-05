package com.substring.foodie.controller;

import com.substring.foodie.config.security.JwtService;
import com.substring.foodie.dto.UserDto;
import com.substring.foodie.entity.User;
import com.substring.foodie.entity.enums.Role;
import com.substring.foodie.playload.*;
import com.substring.foodie.repository.UserRepository;
import com.substring.foodie.service.UserService;
import com.substring.foodie.utils.Helper;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    //register

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    private ModelMapper modelMapper;


    private UserRepository userRepository;

    JwtService jwtService;


    public AuthController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, ModelMapper modelMapper, UserRepository userRepository, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest userRegisterData) {

        if (!userRegisterData.getPassword().equals(userRegisterData.getConfirmPassword())) {
            ApiResponse apiResponse = ApiResponse.builder()
                    .message("confirm password does not match")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .success(false)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        //
        UserDto userDto = new UserDto();
        userDto.setId(Helper.generateRandomId());
        userDto.setName(userRegisterData.getName());
        userDto.setEmail(userRegisterData.getEmail());
        userDto.setPassword(passwordEncoder.encode(userRegisterData.getPassword()));
        userDto.setRole(Role.ROLE_USER);
        userDto.setAvailable(false);
        userDto.setCreatedDate(LocalDate.now());
        userDto.setEnabled(true);
        UserDto createdUser = userService.saveUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    //login

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {


        //created authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        //authenticating
        authenticationManager.authenticate(authentication);

        //getting userdetail
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        UserDto userDto = modelMapper.map(userRepository.findByEmail(userDetails.getUsername()).get(), UserDto.class);
        //getting token
        String jwtToken = jwtService.generateToken(userDto.getEmail(), true);
        String refreshToken = jwtService.generateToken(userDto.getEmail(), false);
        JwtResponse build = JwtResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .user(userDto)
                .build();
        return ResponseEntity.ok(build);

    }

    //refresh token api

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(
            @RequestBody RefreshTokenRequest refreshTokenRequeest
    ) {


        //refresh token ko valildate
        if (jwtService.validateToken(refreshTokenRequeest.getRefreshToken()) && jwtService.isRefreshToken(refreshTokenRequeest.getRefreshToken())) {

            //refresh token se username nikal rehe
            String usernameFromRefreshToken = jwtService.getUsername(refreshTokenRequeest.getRefreshToken());
            //user data fetch kiya hia from database
            UserDto userDto = modelMapper.map(userRepository.findByEmail(usernameFromRefreshToken).get(), UserDto.class);

            //new access token generate kiya hai.
            String accessToken = jwtService.generateToken(userDto.getEmail(), true);
            //new refresh token
            String newRefreshToken = jwtService.generateToken(userDto.getEmail(), false);
            JwtResponse response = JwtResponse
                    .builder()
                    .accessToken(accessToken)
                    .refreshToken(newRefreshToken)
                    .user(userDto).build();

            return ResponseEntity.ok(response);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.builder().httpStatus(HttpStatus.BAD_REQUEST).message("Invalid Refresh Token").success(false).build()
            );
        }


    }


}
