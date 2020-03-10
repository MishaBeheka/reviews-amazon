package boot.project.analyze_reviews_from_amazon.repository;

import java.util.List;

import boot.project.analyze_reviews_from_amazon.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT r.profileName, COUNT(r.profileName) AS activ FROM Review r "
            + "GROUP BY r.profileName ORDER BY activ DESC")
    List<String> showActivityUsers(Pageable pageable);


    @Query(value = "SELECT p.productId, COUNT(p.productId) AS popular FROM Review p "
            + "GROUP BY p.productId ORDER BY popular DESC")
    List<String> showTheMostPopularGoods(Pageable pageable);
}
