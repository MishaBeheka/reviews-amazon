package boot.project.analyze_reviews_from_amazon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ReviewsController {

    @GetMapping
    public String hello() {
        return "Hello from Amazon";
    }
}
