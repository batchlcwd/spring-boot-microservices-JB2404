package com.substring.foodie.repository;

import com.substring.foodie.entity.Address;
import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {


    List<Address> findByUser(User user);


}
