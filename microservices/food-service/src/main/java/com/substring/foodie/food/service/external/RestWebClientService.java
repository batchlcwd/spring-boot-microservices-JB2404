package com.substring.foodie.food.service.external;

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
    private WebClient webClient;

    public RestaurantDto getById(String resId) {

        RestaurantDto restaurantDto = webClient
                .get()
                .uri("/api/v1/restaurants/{id}", resId)
                .retrieve()
                .bodyToMono(RestaurantDto.class)
                .block();

        return restaurantDto;
    }

    // get all restaurants
    public List<RestaurantDto> getAll() {
        return webClient.get()
                .uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDto.class)
                .collectList()
                .block();
    }

    //post request
    public RestaurantDto createRestaurant(RestaurantDto newRestaurant) {
        return webClient
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
        return webClient.get()
                .uri("/api/v1/restaurants/{id}", restId)
                .retrieve()
                .bodyToMono(RestaurantDto.class);
    }


    public Flux<RestaurantDto> getAllNon() {
        return webClient.get()
                .uri("/api/v1/restaurants")
                .retrieve()
                .bodyToFlux(RestaurantDto.class);

    }


}
