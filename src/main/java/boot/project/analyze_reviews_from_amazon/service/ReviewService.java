package boot.project.analyze_reviews_from_amazon.service;

import boot.project.analyze_reviews_from_amazon.entity.Review;

public interface ReviewService {
    void saveAll(Iterable<Review> iterable);
}
