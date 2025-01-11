package com.substring.foodie.service.impl;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.entity.Restaurant;
import com.substring.foodie.exception.ResourceNotFoundException;
import com.substring.foodie.repository.RestaurantRepo;
import com.substring.foodie.service.RestaurantService;
import com.substring.foodie.utils.Helper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepo restaurantRepo;
    private ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepo restaurantRepo, ModelMapper modelMapper) {
        this.restaurantRepo = restaurantRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public RestaurantDto add(RestaurantDto restaurantDto) {

        //id:
        restaurantDto.setId(Helper.generateRandomId());
        restaurantDto.setCreatedDate(LocalDateTime.now());
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        //convert: dto to entity
        Restaurant savedEntity = restaurantRepo.save(restaurant);
        return modelMapper.map(savedEntity, RestaurantDto.class);
    }

    @Override
    public RestaurantDto update(RestaurantDto restaurantDto, String id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        restaurant.setDescription(restaurantDto.getDescription());
        restaurant.setCloseTime(restaurantDto.getCloseTime());
        restaurant.setOpenTime(restaurantDto.getOpenTime());
        restaurant.setOpen(restaurantDto.getOpen());
        Restaurant savedEntity = restaurantRepo.save(restaurant);
        return modelMapper.map(savedEntity, RestaurantDto.class);
    }

    @Override
    public void delete(String id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        restaurantRepo.delete(restaurant);
    }

    @Override
    public RestaurantDto get(String id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    @Override
    public Page<RestaurantDto> getAll(Pageable pageable) {
        Page<Restaurant> pageRestaurant = restaurantRepo.findAll(pageable);
        return pageRestaurant.map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class));

    }

    @Override
    public List<RestaurantDto> searchByName(String keyword) {
        return restaurantRepo.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<RestaurantDto> getOpenRestaurants(Pageable pageable) {
        Page<Restaurant> pageRestaurant = restaurantRepo.findByOpen(true, pageable);
        return pageRestaurant.
                map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class));

    }
}
