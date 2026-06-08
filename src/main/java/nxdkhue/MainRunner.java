package nxdkhue;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainRunner {
    private static final String URL = "https://efadzli.com/software_testing/index.php?view=user_login";

    public static void main(String[] args) {
        boolean headless = "true".equalsIgnoreCase(System.getProperty("headless"));
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
        }

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get(URL);
            Thread.sleep(2000);

            WebElement user = driver.findElement(By.id("username"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", user);
            Thread.sleep(1500);
            user.clear();
            user.sendKeys("Adam");
            Thread.sleep(1500);

            WebElement pass = driver.findElement(By.id("password"));
            pass.clear();
            pass.sendKeys("Adam123");
            Thread.sleep(1500);

            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight * 0.65);");
            Thread.sleep(1500);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
            Thread.sleep(2000);

            WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", status);
            String statusText = status.getText().trim();
            if (statusText.contains("Congratulations")) {
                System.out.println("LOGIN SUCCESS");
                System.out.println("Title: " + driver.getTitle());
                System.out.println("URL: " + driver.getCurrentUrl());
                System.exit(0);
            } else {
                System.err.println("LOGIN FAILED: status text not success -> " + statusText);
                System.exit(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
        } finally {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }
}
