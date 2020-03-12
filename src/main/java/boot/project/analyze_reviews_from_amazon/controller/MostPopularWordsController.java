package boot.project.analyze_reviews_from_amazon.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import boot.project.analyze_reviews_from_amazon.service.serviceImpl.ReviewServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/popular-words")
public class MostPopularWordsController {

    private final ReviewServiceImpl reviewService;

    public MostPopularWordsController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<String> showTheMostPopularWords() {
        Map<String, Long> popularWords = new HashMap<>();
        List<String> reviews = reviewService.getAllReviews();

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
