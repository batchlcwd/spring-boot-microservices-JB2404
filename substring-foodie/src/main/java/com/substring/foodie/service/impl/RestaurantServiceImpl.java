package com.substring.foodie.service.impl;

import com.substring.foodie.dto.RestaurantDto;
import com.substring.foodie.entity.Address;
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
    public RestaurantDto addRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(savedRestaurant, RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto restaurantDto, String restaurantId) {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found"));

        existingRestaurant.setName(restaurantDto.getName());
        existingRestaurant.setDescription(restaurantDto.getDescription());
        existingRestaurant.setOpenTime(restaurantDto.getOpenTime());
        existingRestaurant.setCloseTime(restaurantDto.getCloseTime());
        existingRestaurant.setOpen(restaurantDto.getOpen());
        existingRestaurant.setCreatedDate(restaurantDto.getCreatedDate());
        existingRestaurant.setActive(restaurantDto.isActive());
        existingRestaurant.setBannerImageUrl(restaurantDto.getBannerImageUrl());

        existingRestaurant.setAddress(modelMapper.map(restaurantDto.getAddress(), Address.class));


        //change owner code.

        Restaurant updatedRestaurant = restaurantRepository.save(existingRestaurant);
        return modelMapper.map(updatedRestaurant, RestaurantDto.class);
    }

    @Override
    public void deleteRestaurant(String restaurantId) {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found"));
        restaurantRepository.delete(existingRestaurant);
    }

    @Override
    public RestaurantDto getRestaurant(String restaurantId) {
        Restaurant restaurantNotFound = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant Not Found"));
        return modelMapper.map(restaurantNotFound, RestaurantDto.class);
    }

    @Override
    public Page<RestaurantDto> getRestaurants(Pageable pageable) {
        Page<Restaurant> pageRestaurant = restaurantRepository.findAll(pageable);
        return pageRestaurant.map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class));

    }

    @Override
    public List<RestaurantDto> getByOwner(String ownerId) {
        List<Restaurant> restaurants = restaurantRepository.findByOwnerId(ownerId);
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @Override
    public List<RestaurantDto> searchByName(String nameKeyword) {
        List<Restaurant> restaurants = restaurantRepository.searchByName(nameKeyword);
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @Override
    public List<RestaurantDto> searchByAddress(String address) {
        List<Restaurant> restaurants = restaurantRepository.searchByAddress(address);
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }

    @Override
    public List<RestaurantDto> getByIsActive(Boolean isActive) {
        return List.of();
    }

    @Override
    public List<RestaurantDto> getByOpen(Boolean isOpen) {
        return List.of();
    }

    @Override
    public List<RestaurantDto> getByIsActiveAndOpen(Boolean isActive, Boolean isOpen) {
        List<Restaurant> restaurants = restaurantRepository.findByIsActiveAndOpen(isActive, isOpen);
        return restaurants.stream()
                .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
                .toList();
    }
}
