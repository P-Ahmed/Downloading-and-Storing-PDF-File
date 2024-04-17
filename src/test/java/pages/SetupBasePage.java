package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class SetupBasePage {
    public WebDriver driver;

    @BeforeClass
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public void waiting(int waitingTime) {
        try {
            Thread.sleep(waitingTime);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    @AfterClass
    public void browserClosing() {
        driver.quit();
    }
}
