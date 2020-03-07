package boot.project.analyze_reviews_from_amazon.service.serviceImpl;

import boot.project.analyze_reviews_from_amazon.entity.Review;
import boot.project.analyze_reviews_from_amazon.repository.ReviewRepository;
import boot.project.analyze_reviews_from_amazon.service.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void saveAll(Iterable<Review> iterable) {
        reviewRepository.saveAll(iterable);
    }
}
