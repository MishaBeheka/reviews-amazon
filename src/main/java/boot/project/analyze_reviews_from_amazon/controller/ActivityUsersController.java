package boot.project.analyze_reviews_from_amazon.controller;

import java.util.List;

import boot.project.analyze_reviews_from_amazon.service.serviceImpl.ReviewServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show-users")
public class ActivityUsersController {

    private final ReviewServiceImpl reviewService;

    public ActivityUsersController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<String> showUsers(Model model) {
        Pageable pageable = PageRequest.of(0, 10);
        return reviewService.showActivityUsers(pageable);
    }
}
