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
import boot.project.analyze_reviews_from_amazon.service.ReviewService;
import boot.project.analyze_reviews_from_amazon.util.FileExecutorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class ReadFileController {

    private final ReviewService reviewService;

    public ReadFileController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostConstruct
    public void readFileReviews() {
        List<Review> reviews = new ArrayList<>();
        long readStart = System.nanoTime();
        try {
            Reader reader = new FileReader(FileExecutorUtils.getFileFromLocalResources());
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader().parse(reader);

            reviews = StreamSupport.stream(records.spliterator(), false)
                    .map(this::parseToReview)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("Can't parse file!!!", e);
        }

        log.info("File parsed for {} ms", ((System.nanoTime() - readStart) / 1000000));

        long saveStart = System.nanoTime();
        reviewService.saveAll(reviews);
        log.info("Review size = " + reviews.size());
        log.info("All reviews saved to DB for {} ms", ((System.nanoTime() - saveStart) / 1000000));
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
