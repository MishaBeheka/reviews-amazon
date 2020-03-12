package boot.project.analyze_reviews_from_amazon.service.serviceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import boot.project.analyze_reviews_from_amazon.entity.Review;
import boot.project.analyze_reviews_from_amazon.repository.ReviewRepository;
import boot.project.analyze_reviews_from_amazon.service.ReviewService;
import org.springframework.data.domain.Pageable;
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

    @Override
    public List<String> showActivityUsers(Pageable pageable) {
        return reviewRepository.showActivityUsers(pageable);
    }

    @Override
    public List<String> showTheMostPopularGoods(Pageable pageable) {
        return reviewRepository.showTheMostPopularGoods(pageable);
    }

    @Override
    public List<String> getMostPopularWords() {
        Map<String, Long> popularWords = new HashMap<>();
        List<String> reviews = reviewRepository.getAllReviews();
        selectAllWordsToMap(reviews, popularWords);
        return popularWords.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .map(Map.Entry::getKey)
                .limit(100)
                .collect(Collectors.toList());
    }

    private void selectAllWordsToMap(List<String> reviews,
                                     Map<String, Long> popularWords) {
        reviews.forEach(review -> {
            String[] words = review.toUpperCase()
                    .replaceAll("\\W+", " ")
                    .split(" ");

            Arrays.stream(words).forEach(word -> {
                if (popularWords.containsKey(word)) {
                    Long value = popularWords.remove(word);
                    popularWords.put(word, ++value);
                } else {
                    popularWords.put(word, 0L);
                }
            });
        });
    }
}
