package boot.project.analyze_reviews_from_amazon.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class FileExecutorUtils {
    public static final String LOCAL_PATH_TO_FILE = "src/main/resources/Reviews.csv";
    public static final String REVIEWS_FILE_URL =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";

    public static File getFileFromLocalResources() {

        if (Files.notExists(Paths.get(LOCAL_PATH_TO_FILE))) {
            log.info("Can't find file in the local resource. Try to download file from web resource...");
            getFileFromWebResourceTest();
        }
        return Paths.get(LOCAL_PATH_TO_FILE).toFile();
    }

    private static void getFileFromWebResourceTest() {
        log.info("Downloading in progress. Please wait...");

        try (FileOutputStream fileOutputStream = new FileOutputStream(LOCAL_PATH_TO_FILE);
             InputStream inputStream = URI.create(REVIEWS_FILE_URL).toURL().openStream();
             var channel = Channels.newChannel(inputStream)) {

            fileOutputStream.getChannel().transferFrom(channel, 0, Long.MAX_VALUE);
            log.info("File downloaded with NIO successfully!");
        } catch (IOException e) {
            throw new RuntimeException("Can't download Reviews.csv file!", e);
        }
    }
}