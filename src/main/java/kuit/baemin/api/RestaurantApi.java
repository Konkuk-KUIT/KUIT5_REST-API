package kuit.baemin.api;

import kuit.baemin.application.RestaurantService;
import kuit.baemin.dto.request.RestaurantRequest;
import kuit.baemin.dto.response.RestaurantResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<RestaurantResponse> registerRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse response = restaurantService.registerRestaurant(restaurantRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
