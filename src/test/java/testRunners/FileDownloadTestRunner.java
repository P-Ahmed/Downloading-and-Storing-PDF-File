package testRunners;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SetupBasePage;
import utils.Utility;

import java.io.File;
import java.time.Duration;
import java.util.Set;

public class FileDownloadTestRunner extends SetupBasePage {
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test(priority = 1)
    public void openingTheFileInNewTab() {
        driver.get(Utility.readThePropertyFile("url"));

        WebElement pdfLink = driver.findElement(By.linkText("Download a Printable PDF of this Cheat Sheet"));
        wait.until(ExpectedConditions.elementToBeClickable(pdfLink));
        pdfLink.click();

        waiting(3000);
    }

    @Test(priority = 2)
    public void switchingToNewTab() {
        String mainWindowHandle = driver.getWindowHandle();

        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        waiting(3000);
    }

    @Test(priority = 3)
    public void downloadingTheFile() {
        String pdfUrl = driver.getCurrentUrl();
        String[] urlParts = pdfUrl.split("/");
        String filename = urlParts[urlParts.length - 1];
        Utility.writeIntoPropertyFile("downloadedFileName", filename);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var xhr = new XMLHttpRequest();" +
                "xhr.open('GET', arguments[0], true);" +
                "xhr.responseType = 'blob';" +
                "xhr.onload = function () {" +
                "    var a = document.createElement('a');" +
                "    a.href = window.URL.createObjectURL(xhr.response);" +
                "    a.download = '" + filename + "';" +
                "    document.body.appendChild(a);" +
                "    a.click();" +
                "    document.body.removeChild(a);" +
                "};" +
                "xhr.send();", pdfUrl);

        waiting(3000);
    }

    @Test(priority = 4)
    public void verifyingTheFileIsDownloadedOrNot() {
        String directoryPath = Utility.readThePropertyFile("downloadedDirectory");
        String fileName = Utility.readThePropertyFile("downloadedFileName");

        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File file = new File(directory, fileName);

            if (file.exists() && file.isFile()) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        } else {
            Assert.fail();
        }
    }
}
