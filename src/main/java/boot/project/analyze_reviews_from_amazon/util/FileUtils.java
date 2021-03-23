package boot.project.analyze_reviews_from_amazon.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class FileUtils {
    public static final String PATH_TO_FILE_RESOURCES = "src/main/resources/Reviews.csv";
    public static final String PATH_DOWNLOAD_FILE =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";

    public static File getFileFromResources() {
        if (!new File(PATH_TO_FILE_RESOURCES).exists()) {
            log.info("Cannot find in the filesystem. Trying to download it from internet");
            getFileFromInternet();
        }
        return new File(PATH_TO_FILE_RESOURCES);
    }

    private static void getFileFromInternet() {
        log.info("Downloading in progress. Please wait!");
        try (BufferedInputStream in =
                     new BufferedInputStream(new URL(PATH_DOWNLOAD_FILE).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_FILE_RESOURCES)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            log.info("Download was successful!");
        } catch (IOException e) {
            throw new RuntimeException("Cannot download file");
        }
    }
}