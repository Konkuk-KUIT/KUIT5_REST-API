package kuit.baemin.service;

import kuit.baemin.domain.Review;
import kuit.baemin.dto.ReviewRequest;
import kuit.baemin.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional
    public Review save(Long userId, Long storeId, ReviewRequest request) {
        Review review = new Review(
                null,  // reviewId는 자동 생성됨
                storeId,
                userId,
                request.getScope(),
                request.getComment(),
                null  // createdAt은 DB default 값 사용
        );
        return reviewRepository.save(review);
    }

    @Transactional(readOnly = true)
    public List<Review> findReviewsByStoreWithPaging(Long storeId, int size, Long lastReviewId) {
        return reviewRepository.findReviewsByStoreWithPaging(storeId, size, lastReviewId);
    }

}
