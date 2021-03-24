package boot.project.analyze_reviews_from_amazon.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Slf4j
public class FileUtils {
    public static final String PATH_TO_FILE_REVIEWS = "src/main/resources/Reviews.csv";
    public static final String PATH_DOWNLOAD_FILE_REVIEWS =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";

    public static final String PATH_TO_IMAGE_FILE_AMAZON_1 = "src/main/resources/static/images/1383586.jpg";
    public static final String PATH_DOWNLOAD_FILE_LOGO_AMAZON_1 = "https://wallpaperaccess.com/download/amazon-logo-1383586";

    public static final String PATH_TO_IMAGE_FILE_AMAZON_2 = "src/main/resources/static/images/1383593.png";
    public static final String PATH_DOWNLOAD_FILE_LOGO_AMAZON_2 = "https://wallpaperaccess.com/download/amazon-logo-1383593";

    public static final String PATH_TO_IMAGE_FILE_AMAZON_3 = "src/main/resources/static/images/1383657.jpg";
    public static final String PATH_DOWNLOAD_FILE_LOGO_AMAZON_3 = "https://wallpaperaccess.com/download/amazon-logo-1383657";

    public static File getFileFromLocalResources() {
        if (!new File(PATH_TO_FILE_REVIEWS).exists()) {
            log.info("Ca't find file in the local resource. Try to download file from web resource");
            getFileFromWebResource();
        }
        if (!new File(PATH_TO_IMAGE_FILE_AMAZON_1).exists() &&
                !new File(PATH_TO_IMAGE_FILE_AMAZON_2).exists() &&
                !new File(PATH_TO_IMAGE_FILE_AMAZON_3).exists()) {
            getImagesFromWebResource();
        }
        return new File(PATH_TO_FILE_REVIEWS);
    }

    private static void getFileFromWebResource() {
        log.info("Downloading in progress. Please wait...");
        try (BufferedInputStream in =
                     new BufferedInputStream(new URL(PATH_DOWNLOAD_FILE_REVIEWS).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_FILE_REVIEWS)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            log.info("Download was successful!");
        } catch (IOException e) {
            throw new RuntimeException("Can't download Reviews.csv file!");
        }
    }

    private static void getImagesFromWebResource() {
        log.info("Downloading in progress. Please wait...");
        try (BufferedInputStream in =
                     new BufferedInputStream(new URL(PATH_DOWNLOAD_FILE_LOGO_AMAZON_1).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_IMAGE_FILE_AMAZON_1)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            log.info("Download image was successful!");

        } catch (IOException e) {
            throw new RuntimeException("Can't download image file");
        }

        try (BufferedInputStream in =
                     new BufferedInputStream(new URL(PATH_DOWNLOAD_FILE_LOGO_AMAZON_2).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_IMAGE_FILE_AMAZON_2)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            log.info("Download image was successful!");

        } catch (IOException e) {
            throw new RuntimeException("Can't download image file");
        }

        try (BufferedInputStream in =
                     new BufferedInputStream(new URL(PATH_DOWNLOAD_FILE_LOGO_AMAZON_3).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(PATH_TO_IMAGE_FILE_AMAZON_3)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            log.info("Download image was successful!");

        } catch (IOException e) {
            throw new RuntimeException("Can't download image file");
        }
    }
}