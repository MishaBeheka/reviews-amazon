package boot.project.analyze_reviews_from_amazon.repository;

import boot.project.analyze_reviews_from_amazon.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
