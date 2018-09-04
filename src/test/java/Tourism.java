import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Tourism {
    public static WebDriver driver;

    @BeforeClass
    public static void goToGoogle()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com.ua");
    }

    private void check(String locator)
    {
         String nameFile = String.valueOf((driver.findElement(By.xpath(locator)).getText()));
         if (nameFile.contains("Туроператор")) {
             System.out.println(nameFile);
         }
         else {
            System.out.println("Не имеет в названии 'Туроператор' " + nameFile.contains("Туроператор"));
         }
    }

    @Test
    public void findTourism()
    {
        WebElement search = driver.findElement(By.xpath("//div[2]/div/input"));
        search.sendKeys("Туроператор");
        driver.findElement(By.xpath("//span/input")).click();

        check( "//div[@id='rso']/div/div/div/div/div/h3/a" );
        check( "//div[2]/div/div/h3/a");
        check( "//div[3]/div/div/h3/a");
        check( "//div[4]/div/div/h3/a");
        check( "//div[3]/div/div/div/div/h3/a" );
        check( "//div[3]/div/div[2]/div/div/h3/a");
        check( "//div[5]/div/div[2]/div/div/h3/a");
        check( "//div[5]/div/div[3]/div/div/h3/a");
        check( "//div[4]/div/div/h3/a");

        driver.findElement(By.xpath("//td[3]/a")).click();
        Set<String> oldWindowsSet = driver.getWindowHandles();
        WebElement element = driver.findElement(By.xpath("//div[@id='tads']/ol/li/div/h3/a[2]"));
        element.sendKeys(Keys.LEFT_CONTROL,Keys.LEFT_SHIFT, Keys.ENTER);
        Set<String> newWindowsSet = driver.getWindowHandles();
        newWindowsSet.removeAll(oldWindowsSet);

        if (oldWindowsSet.contains(newWindowsSet)){
           System.out.println("Вкладка не открыта");
         }
         else {
            System.out.println("Переход по вкладке осуществлен");
         }
    }

    @AfterClass
    public static void end() {
        driver.quit();
    }
}

