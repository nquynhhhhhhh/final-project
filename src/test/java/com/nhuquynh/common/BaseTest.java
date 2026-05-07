package com.nhuquynh.common;

import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.CaptureHelper;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeSuite
    public void setupEnviroment(){
        PropertiesHelper.loadAllFiles(); // để đa tránh xung đột
    }

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) { //nếu muốn ưu tiên xml thì lấy biến này (lấy từ Parameters của xml)
        WebDriver driver;
        if (PropertiesHelper.getValue("BROWSER").isEmpty() && PropertiesHelper.getValue("BROWSER").isBlank()){
            browser = browser;
        }else {
            browser = PropertiesHelper.getValue("BROWSER");
        }

        switch (browser){
            case "chrome":
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt ChromeTest");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("Khởi chạy trình duyệt Firefox");
                break;
            default:
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt ChromeTest mặc định");
                break;
        }

        DriverManager.setDriver(driver);  //set giá trị driver vào ThreadLocal
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }
    @AfterMethod
    public void closeDriver() {
        CaptureHelper.stopRecord(1);
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }

}
