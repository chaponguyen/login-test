package nxdkhue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {
    private WebDriver driver;
    private final String url = "https://efadzli.com/software_testing/index.php?view=user_login";

    @BeforeAll
    void setUp() {
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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
        Thread.sleep(1500);
        submit.click();
        Thread.sleep(1500);

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", status);
        Thread.sleep(1500);
        String statusText = status.getText().trim();

        Assertions.assertEquals("Congratulations!", statusText, "Expected status to show success message after submitting.");
    }

    @Test
    
    void testLoginKhue() throws InterruptedException {
        driver.get(url);
        Thread.sleep(2000);
        WebElement user = driver.findElement(By.id("username"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", user);
        Thread.sleep(1500);
        user.clear();
        user.sendKeys("Khue");
        Thread.sleep(1500);

        WebElement pass = driver.findElement(By.id("password"));
        pass.clear();
        pass.sendKeys("Khue123");
        Thread.sleep(1500);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("submitButton")));
        Thread.sleep(1500);
        submit.click();
        Thread.sleep(1500);

        WebElement status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("status")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior:'smooth',block:'center'});", status);
        Thread.sleep(1500);
        String statusText = status.getText().trim();

        Assertions.assertEquals("Congratulations!", statusText, "Expected status to show success message after submitting.");
    }
}
