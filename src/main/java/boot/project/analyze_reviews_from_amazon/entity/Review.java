package boot.project.analyze_reviews_from_amazon.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "profile_name")
    private String profileName;

    @Column(name = "score")
    private Long score;

    @Column(name = "review", columnDefinition = "TEXT")
    private String text;
}
