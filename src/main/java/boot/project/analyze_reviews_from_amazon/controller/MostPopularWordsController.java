package boot.project.analyze_reviews_from_amazon.controller;

import java.util.List;

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
        return reviewService.getMostPopularWords();
    }
}
