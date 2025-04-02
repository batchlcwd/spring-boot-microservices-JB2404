package com.substring.foodie.repository;

import com.substring.foodie.entity.DeliveryEarning;
import com.substring.foodie.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DeliveryEarningRepository extends JpaRepository<DeliveryEarning, Long> {


    List<DeliveryEarning> findByDeliveryBoy(User deliveryBoy);


}
