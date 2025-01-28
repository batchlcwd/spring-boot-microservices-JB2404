package com.substring.foodie.service.impl;

import com.substring.foodie.config.AppConstants;
import com.substring.foodie.dto.UserDto;
import com.substring.foodie.entity.RoleEntity;
import com.substring.foodie.entity.User;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.RoleRepo;
import com.substring.foodie.repository.UserRepo;
import com.substring.foodie.service.UserService;
import com.substring.foodie.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private final ResourcePatternResolver resourcePatternResolver;
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    private RoleRepo roleRepo;

    private ModelMapper modelMapper;

    public UserServiceImpl(ResourcePatternResolver resourcePatternResolver, UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo, ModelMapper modelMapper) {
        this.resourcePatternResolver = resourcePatternResolver;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        //generate new id for user
        userDto.setId(Helper.generateRandomId());
        User user = convertUserDtoToUser(userDto);
        //save the user to database
        //we are encoding password :
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // guest : role
        RoleEntity roleGuest = roleRepo.findByName(AppConstants.getRoleGuest());
        user.getRoleEntities().add(roleGuest);
        User savedUser = userRepo.save(user);
        return convertUserDtoToUserDto(savedUser);


    }

    //TODO:
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        return null;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        Page<User> userPage = userRepo.findAll(pageable);

        //return after converting to Page<UserDto>
        return userPage.map(user -> convertUserDtoToUserDto(user));


    }

    @Override
    public List<UserDto> getUserByName(String userName) {
        return userRepo.findByName(userName).stream().map(
                (user) -> convertUserDtoToUserDto(user)
        ).toList();
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("User not found !!")
        );
        return convertUserDtoToUserDto(user);
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found !!")
        );
        return convertUserDtoToUserDto(user);
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found !!")
        );
        userRepo.delete(user);
    }

    //   TODO::
    @Override
    public List<UserDto> searchUserName(String keyword) {
        return List.of();
    }

    private User convertUserDtoToUser(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setPhoneNumber(userDto.getPhoneNumber());
//        user.setAddress(userDto.getAddress());

        return modelMapper.map(userDto, User.class);
    }

    private UserDto convertUserDtoToUserDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setPhoneNumber(user.getPhoneNumber());
//        userDto.setAddress(user.getAddress());
//        return userDto;
        return modelMapper.map(user, UserDto.class);

    }

    //
//    private UserRepo userRepo;
//
//
//    public UserServiceImpl(UserRepo userRepo) {
//        this.userRepo = userRepo;
//    }
//
//
//    @Override
//
//    public User saveUser(User user) {
//        user.setId(UUID.randomUUID().toString());
//        User savedEntity = userRepo.save(user);
//        return savedEntity;
//    }
//
//    @Transactional
//    public User updateUser(User user, String userId) {
//        // get user
//        // usko ek naya restaurant add karnuga
//
//        User user1 = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
//        user1.setName(user.getName());
////        ..all fields
//        User save = userRepo.save(user1);
//        return save;
//    }
//
//    @Override
//    public void testUserRole() {
//
//        User user = new User();
//        user.setId(UUID.randomUUID().toString());
//        user.setName("Vivek Ashok");
//        user.setEmail("vivek@gmail.com");
//        user.setAvailable(true);
//        user.setAddress("Testing address for many to many");
//        user.setPassword("abc");
//
//
//        //
//        RoleEntity entity1 = new RoleEntity();
//        entity1.setName("ROLE_ADMIN");
//
//
//        RoleEntity entity2 = new RoleEntity();
//        entity2.setName("ROLE_GUEST");
//
//        //link
//        //user ki taraf se
//        user.getRoleEntities().add(entity1);
//        user.getRoleEntities().add(entity2);
//
//        // entities
//        entity1.getUsers().add(user);
//        entity2.getUsers().add(user);
//
//        userRepo.save(user);
//
//        System.out.println("user saved:");
//
//
//    }
}
