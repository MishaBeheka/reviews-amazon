package boot.project.analyze_reviews_from_amazon.service;

import java.util.List;

import boot.project.analyze_reviews_from_amazon.entity.Review;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    void saveAll(Iterable<Review> iterable);

    List<String> showActivityUsers(Pageable pageable);

    List<String> showTheMostPopularGoods(Pageable pageable);

    List<String> getAllReviews();
}
