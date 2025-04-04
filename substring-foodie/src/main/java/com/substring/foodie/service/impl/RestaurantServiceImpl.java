package com.substring.foodie.service.impl;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.RestaurantRepository;
import com.substring.foodie.service.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    private ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestaurantDto addRestaurant(RestaurantDto restaurant) {
        return null;
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurant, String userId) {
        return null;
    }

    @Override
    public void deleteRestaurant(String userId) {

    }

    @Override
    public RestaurantDto getRestaurant(String restaurantId) {
        Restaurant restaurantNotFound = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found"));
        return modelMapper.map(restaurantNotFound, RestaurantDto.class);
    }

    @Override
    public Page<RestaurantDto> getRestaurants(Pageable pageable) {
        Page<Restaurant> pageRestaurant = restaurantRepository.findAll(pageable);
        return pageRestaurant.map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class));

    }

    @Override
    public List<RestaurantDto> getByOwner(String ownerId) {
        return List.of();
    }

    @Override
    public List<RestaurantDto> searchByName(String nameKeyword) {
        return List.of();
    }

    @Override
    public List<RestaurantDto> searchByAddress(String address) {
        return List.of();
    }

    @Override
    public List<RestaurantDto> getByIsActive(Boolean isActive) {
        return List.of();
    }

    @Override
    public List<RestaurantDto> getByOpen(Boolean isOpen) {
        return List.of();
    }
}
