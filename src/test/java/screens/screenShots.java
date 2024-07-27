package screens;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class screenShots {
    public static void takeScreenshot(WebDriver driver, String testCaseName, WebElement element)
            throws IOException, IOException {
        // Cast driver object to TakesScreenshot
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        try {
            // Get the screenshot as an image file
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            // Convert file to BufferedImage
            BufferedImage fullImg = ImageIO.read(srcFile);

            // Get the location of element on the page
            Point point = element.getLocation();

            // Get width and height of the element
            int eleWidth = element.getSize().getWidth();
            int eleHeight = element.getSize().getHeight();

            // Draw a red box around the element
            Graphics2D graphics = fullImg.createGraphics();
            graphics.setColor(Color.RED);
            graphics.setStroke(new BasicStroke(4));
            graphics.drawRect(point.getX(), point.getY(), eleWidth, eleHeight);
            graphics.dispose();

            // Define the directory where screenshots will be saved
            String directoryPath = System.getProperty("user.dir") + "/screenShots/";

            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                boolean isDirectoryCreated = directory.mkdirs();
                if (!isDirectoryCreated) {
                    System.out.println("Failed to create directory for screenshots.");
                    return;
                }
            }

            // Define the destination file name
            File destFile = new File(directoryPath + testCaseName + ".png");

            // Save the new image with the red box
            ImageIO.write(fullImg, "png", destFile);

            byte[] fileContent = FileUtils.readFileToByteArray(destFile);
            Allure.addAttachment(testCaseName, new ByteArrayInputStream(fileContent));

            System.out.println("Screenshot taken for test case: " + testCaseName);
        } catch (IOException e) {
            System.out.println("An error occurred while taking screenshot: " + e.getMessage());
        }
    }
}