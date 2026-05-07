package com.nhuquynh.pages;

import com.nhuquynh.common.BasePage;
import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.keywords.WebUI;
import com.nhuquynh.utils.LogUtils;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public LoginPage() {
        super();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Login");
    }

    //Khai báo các element dạng đối tượng By (phương thức tìm kiếm)
    private By headerPage = By.xpath("//h1[normalize-space()='Login']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[contains(@class,'alert-danger')]");
    private By errorMessage1 = By.xpath("(//div[contains(@class,'alert-danger')])[1]");
    private By errorMessage2 = By.xpath("(//div[contains(@class,'alert-danger')])[2]");

    public DashboardPage loginCRM() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.setText(inputEmail, PropertiesHelper.getValue("EMAIL"));
        WebUI.setText(inputPassword, PropertiesHelper.getValue("PASSWORD"));
        WebUI.clickElement(buttonLogin);
        verifyLoginSuccess();
        return new DashboardPage();
    }

    public void loginCRMSuccessWithDataExcel() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);

//        ExcelHelper excelHelper = new ExcelHelper();
//        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Login");

        WebUI.setText(inputEmail, excelHelper.getCellData("Email", 1));
        WebUI.setText(inputPassword, excelHelper.getCellData("Password", 1));
        WebUI.clickElement(buttonLogin);
    }

    public void loginCRMSuccessWithDataProvider(String testcase, String email, String password) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);
        WebUI.setText(inputEmail, email);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
    }

    public void loginCRMFailWithEmailInvalid() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);

//        ExcelHelper excelHelper = new ExcelHelper();
//        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Login");

        WebUI.setText(inputEmail, excelHelper.getCellData("Email", 2));
        WebUI.setText(inputPassword, excelHelper.getCellData("Password", 2));
        WebUI.clickElement(buttonLogin);
    }

    public void loginCRMFailWithPasswordInvalid() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputEmail);
        WebUI.clearText(inputPassword);

//        ExcelHelper excelHelper = new ExcelHelper();
//        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Login");

        WebUI.setText(inputEmail, excelHelper.getCellData("Email", 3));
        WebUI.setText(inputPassword, excelHelper.getCellData("Password", 3));
        WebUI.clickElement(buttonLogin);
    }

    public void verifyLoginSuccess() {
        WebUI.waitForPageLoaded();
        WebUI.assertNotContains(WebUI.getCurrentURL(), "authentication", "FAIL. Vẫn đang ở trang Login");
    }

    public void verifyLoginFail(String message) {
        WebUI.assertContains(WebUI.getCurrentURL(), "authentication", "FAIL. Vẫn đang ở trang Login");
        Assert.assertTrue(DriverManager.getDriver().findElement(errorMessage).isDisplayed(), "Error message NOT displays");
        WebUI.assertEquals(WebUI.getElementText(errorMessage), "Invalid email or password", message);
    }

}

