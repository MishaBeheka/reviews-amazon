package boot.project.analyze_reviews_from_amazon.controller;

import java.util.List;

import boot.project.analyze_reviews_from_amazon.service.serviceImpl.ReviewServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/popular-goods")
public class MostPopularGoodsController {

    private final ReviewServiceImpl reviewService;

    public MostPopularGoodsController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<String> showPopularGoods() {
        Pageable pageable = PageRequest.of(0, 10);
        return reviewService.showTheMostPopularGoods(pageable);
    }
}
