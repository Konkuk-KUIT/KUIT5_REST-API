package kuit.baemin.service;

import kuit.baemin.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public void likeRestaurant(Long userId, Long restaurantId) {
        likeRepository.insertFavorite(userId, restaurantId);
    }

    public void unlikeRestaurant(Long userId, Long restaurantId) {
        likeRepository.deleteFavorite(userId, restaurantId);
    }
}

