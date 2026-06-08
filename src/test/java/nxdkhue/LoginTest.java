package nxdkhue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.HttpURLConnection;
import java.net.URI;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {
    private WebDriver driver;
    private final String url = "https://efadzli.com/software_testing/index.php?view=user_login";

    private boolean isWebsiteReachable() {
        try {
            HttpURLConnection connection = (HttpURLConnection)
                    URI.create(url).toURL().openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setInstanceFollowRedirects(false);
            int code = connection.getResponseCode();
            return code == 200 || code == 302 || code == 301;
        } catch (Exception e) {
            return false;
        }
    }

    @BeforeAll
    void setUp() {
        Assumptions.assumeTrue(isWebsiteReachable(),
                "Website is unreachable - skipping tests");

        ChromeOptions options = new ChromeOptions();
        String headless = System.getProperty("headless");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--remote-allow-origins=*");
        }
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @AfterAll
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    
    void testLoginAdam() throws InterruptedException {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        WebElement user = driver.findElement(By.id("username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", user);
        Thread.sleep(500);
        user.clear();
        user.sendKeys("Adam");

        WebElement pass = driver.findElement(By.id("password"));
        pass.clear();
        pass.sendKeys("Adam123");

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
        submit.click();

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", status);
        Thread.sleep(500);
        String statusText = status.getText().trim();

        Assertions.assertEquals("Congratulations!", statusText, "Expected status to show success message after submitting.");
    }

    @Test
    
    void testLoginKhue() throws InterruptedException {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        WebElement user = driver.findElement(By.id("username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", user);
        Thread.sleep(500);
        user.clear();
        user.sendKeys("Khue");

        WebElement pass = driver.findElement(By.id("password"));
        pass.clear();
        pass.sendKeys("Khue123");

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
        submit.click();

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", status);
        Thread.sleep(500);
        String statusText = status.getText().trim();

        Assertions.assertEquals("Congratulations!", statusText, "Expected status to show success message after submitting.");
    }

    /*
    @Test
    void testLoginSinhvienTLU() throws InterruptedException {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        WebElement user = driver.findElement(By.id("username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", user);
        Thread.sleep(500);
        user.clear();
        user.sendKeys("sinhvienTLU");

        WebElement pass = driver.findElement(By.id("password"));
        pass.clear();
        pass.sendKeys("SinhvienTLU123");

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
        submit.click();

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", status);
        Thread.sleep(500);
        String statusText = status.getText().trim();

        Assertions.assertEquals("Congratulations!", statusText, "Expected status to show success message after submitting.");
    }
    */
}
