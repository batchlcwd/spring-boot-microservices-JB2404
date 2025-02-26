package com.substring.foodie.food.service.external;

import com.substring.foodie.food.config.AppConstants;
import com.substring.foodie.food.dto.RestaurantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class RestWebClientService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public RestaurantDto getById(String resId) {

        RestaurantDto restaurantDto = webClientBuilder.baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build()
                .get()
                .uri("/api/v1/restaurants/{id}", resId)
                .retrieve()
                .bodyToMono(RestaurantDto.class)
                .block();

        return restaurantDto;
    }

    // get all restaurants
    public List<RestaurantDto> getAll() {
        return  webClientBuilder.baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build()
                .get()
                .uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDto.class)
                .collectList()
                .block();
    }

    //post request
    public RestaurantDto createRestaurant(RestaurantDto newRestaurant) {
        return  webClientBuilder.baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build()
                .post()
                .uri("/api/v1/restaurants")
                .bodyValue(newRestaurant)
                .header("Athorization", "Bearer asdgag")
                .retrieve()
                .bodyToMono(RestaurantDto.class)
                .block();
    }

    // non blocking:

    // get by id

    public Mono<RestaurantDto> getResbyId(String restId) {
        return  webClientBuilder.baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build().get()
                .uri("/api/v1/restaurants/{id}", restId)
                .retrieve()
                .bodyToMono(RestaurantDto.class);
    }


    public Flux<RestaurantDto> getAllNon() {
        return  webClientBuilder.baseUrl(AppConstants.RESTAURANT_SERVICE_URL)
                .build().get()
                .uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDto.class);

    }


}
