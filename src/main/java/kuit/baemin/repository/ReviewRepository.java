package kuit.baemin.repository;

import kuit.baemin.domain.Review;

import java.util.List;

public interface ReviewRepository {
    Review save(Review review);
    List<Review> findReviewsByStoreWithPaging(Long storeId, int size, Long lastReviewId);
}
