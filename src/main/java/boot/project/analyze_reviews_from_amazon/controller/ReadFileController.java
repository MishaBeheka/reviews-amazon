package boot.project.analyze_reviews_from_amazon.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;

import boot.project.analyze_reviews_from_amazon.entity.Review;
import boot.project.analyze_reviews_from_amazon.service.serviceImpl.ReviewServiceImpl;
import boot.project.analyze_reviews_from_amazon.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ReadFileController {

    private final ReviewServiceImpl reviewService;

    public ReadFileController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void readFileReviews() {
        List<Review> reviews = new ArrayList<>();
        try {
            Reader reader = new FileReader(FileUtils.getFileFromLocalResources());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader().parse(reader);

            reviews = StreamSupport.stream(records.spliterator(), false)
                    .map(this::parseToReview)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            log.error("Can't read file: " + e);
        }

        reviewService.saveAll(reviews);
        log.info("Review size = " + reviews.size());
    }

    private Review parseToReview(CSVRecord record) {
        Review review = new Review();
        review.setId(Long.parseLong(record.get(0)));
        review.setProductId(record.get(1));
        review.setUserId(record.get(2));
        review.setProfileName(record.get(3));
        review.setScore(Long.parseLong(record.get(6)));
        review.setText(record.get(9));
        return review;
    }
}
