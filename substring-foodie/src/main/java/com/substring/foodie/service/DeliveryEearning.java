package com.substring.foodie.service;

import com.substring.foodie.dto.DeliveryEarningDto;
import com.substring.foodie.entity.DeliveryEarning;
import com.substring.foodie.entity.User;

import java.util.List;

public interface DeliveryEearning {

    List<DeliveryEarningDto> getDeliveryEarningByDeliveryBoy(String deliveryBoyId);

}
