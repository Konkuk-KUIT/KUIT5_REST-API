package kuit.baemin.controller;

import kuit.baemin.domain.Review;
import kuit.baemin.dto.ReviewRequest;
import kuit.baemin.service.ReviewService;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/users/{userId}/stores/{storeId}/reviews")
    public BaseResponse<Review> createReview(
            @PathVariable Long userId,
            @PathVariable Long storeId,
            @RequestBody @Validated ReviewRequest request
    ) {
        Review review = reviewService.save(userId, storeId, request);
        return new BaseResponse<>(review);
    }

    @GetMapping("/users/{userId}/stores/{storeId}/reviews")
    public BaseResponse<List<Review>> findReviewsByStoreWithPaging(
            @PathVariable Long storeId,
            @RequestParam int size,
            @RequestParam(required = false) Long lastReviewId
    ) {
        List<Review> reviews = reviewService.findReviewsByStoreWithPaging(storeId, size, lastReviewId);
        return new BaseResponse<>( reviews);
    }

}
