package boot.project.analyze_reviews_from_amazon.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import boot.project.analyze_reviews_from_amazon.entity.Review;
import boot.project.analyze_reviews_from_amazon.service.serviceImpl.ReviewServiceImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadFileController {

    private static final Logger LOGGER = LogManager.getLogger(ReadFileController.class);

    private final ReviewServiceImpl reviewService;

    public ReadFileController(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void readFile() {
        List<Review> reviews = new ArrayList<>();
        try {
            Reader reader = new FileReader("E:\\My projects\\Reviews.csv");
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                Review review = new Review();
                review.setId(Long.parseLong(record.get(0)));
                review.setProductId(record.get(1));
                review.setUserId(record.get(2));
                review.setProfileName(record.get(3));
                review.setScore(Long.parseLong(record.get(6)));
                review.setText(record.get(9));
                reviews.add(review);
            }
        } catch (IOException e) {
            LOGGER.error("Can't read file " + e);
        }
        reviewService.saveAll(reviews);
    }
}
