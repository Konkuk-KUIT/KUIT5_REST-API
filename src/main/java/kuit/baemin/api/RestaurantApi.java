package kuit.baemin.api;

import kuit.baemin.application.RestaurantService;
import kuit.baemin.dto.response.RestaurantResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantResponse> getRestaurant(@PathVariable Long restaurantId){
        RestaurantResponse response = restaurantService.getRestaurantDetail(restaurantId);
        return ResponseEntity.ok(response);
    }

}
