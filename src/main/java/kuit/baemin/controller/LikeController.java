package kuit.baemin.controller;

import kuit.baemin.service.LikeService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likeRestaurant(@PathVariable("id") Long restaurantId) {
        Long userId = 1L;
        likeService.likeRestaurant(userId, restaurantId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/like")
    public ResponseEntity<Void> unlikeRestaurant(@PathVariable("id") Long restaurantId) {
        Long userId = 1L;
        likeService.unlikeRestaurant(userId, restaurantId);
        return ResponseEntity.noContent().build();
    }
}
