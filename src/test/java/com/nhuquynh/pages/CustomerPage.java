package com.nhuquynh.pages;

import com.nhuquynh.common.BasePage;
import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.keywords.WebUI;
import com.nhuquynh.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class CustomerPage extends BasePage {
    public CustomerPage() {
        super();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Customer");
    }

    //ADD NEW CUSTOMER
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By headerCustomerPage = By.xpath("//span[normalize-space()='Customers Summary']");
    private By buttonAddNewCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By buttonImportCustomers = By.xpath("//a[normalize-space()='Import Customers']");
    private By inputSearchCustomer = By.xpath("//div[@id='clients_filter']//input[@type='search']");
    private By tabCustomerDetails = By.xpath("//a[normalize-space()='Customer Details']/preceding::hi4");
    private By labelCompany = By.xpath("//label[@for='company']");
    private By inputCompany = By.xpath("//input[@id='company']");
    private By inputVAT = By.xpath("//input[@id='vat']");
    private By inputPhoneNumber = By.xpath("//input[@id='phonenumber']");
    private By inputWebsite = By.xpath("//input[@id='website']");
    private By dropdownGroup = By.xpath("//button[@data-id='groups_in[]']");
    private By labelGroup = By.xpath("//label[normalize-space()='Groups']");
    private By addGroup = By.xpath("//div[@class='input-group-btn']");
    private By inputSearchGroup = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//input");
    private By itemGroup(String groupName) {
        By xpathValueGroup = By.xpath("//button[@data-id='groups_in[]']/following-sibling::div//span[normalize-space()='" + groupName + "']");
        return xpathValueGroup;
    }
    private By dropdownCurrency = By.xpath("//button[@data-id='default_currency']");
    private By inputSearchCurrency = By.xpath("//button[@data-id='default_currency']/following-sibling::div//input");
    private By itemCurrency(String currency) {
        By xpathValueCurrency = By.xpath("(//button[@data-id='default_currency']/following-sibling::div)//span[contains(normalize-space(),'" + currency + "')]");
        return xpathValueCurrency;
    }
    private By notFoundCurrency = By.xpath("//button[@data-id='default_currency']/following-sibling::div//li[@class='no-results']");
    private By dropdownLanguage = By.xpath("//button[@data-id='default_language']");
    private By itemLanguage(String language) {
        By xpathValueLanguage = By.xpath("//button[@data-id='default_language']/following-sibling::div//a[normalize-space()='" + language + "']");
        return xpathValueLanguage;
    }
    private By inputAddress = By.xpath("//textarea[@id='address']");
    private By inputCity = By.xpath("//input[@id='city']");
    private By inputState = By.xpath("//input[@id='state']");
    private By inputZip = By.xpath("//input[@id='zip']");
    private By dropdownCountry = By.xpath("//button[@data-id='country']");
    private By inputSearchCountry = By.xpath("//button[@data-id='country']/following-sibling::div//input");
    private By itemCountry(String country) {
        By xpathValueCountry = By.xpath("//button[@data-id='country']/following-sibling::div//span[normalize-space()='" + country + "']");
        return xpathValueCountry;
    }
    private By buttonSaveAndCreate = By.xpath("//button[normalize-space()='Save and create contact']");
    private By buttonSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");
    private By errorCompany = By.xpath("//p[@id='company-error']");

    //ADD NEW CUSTOMER SUCCESS
    private By alertAddSuccess = By.xpath("//div[@id='alert_float_1']/span[normalize-space()='Customer added successfully.']");
    private By itemCustomerFirst = By.xpath("//table[@id='clients']/tbody/tr[1]/td[3]/a");
//    private By itemCompanyNameOnTable(String customerNumber, String customerName) {
//        By xpathTaskFirstOnTable = By.xpath("//tbody/tr[.//td[contains(text(),'"+ customerNumber +"')] and .//a[contains(text(),'"+ customerName +"')]]//a[normalize-space()='"+ customerName +"']");
//        return xpathTaskFirstOnTable;
//    }
    private By headerCustomerDetailPage = By.xpath("//h4[normalize-space()='Profile']");

    //total in customer list
    private By totalCustomers = By.xpath("//span[normalize-space()='Total Customers']/preceding-sibling::span");


    //========================HÀM CHUNG===============================
    public void verifyNavigateToCustomerPage() {
        Assert.assertTrue(WebUI.checkElementExist(headerCustomerPage), "The customer header page not dissplay.");
        Assert.assertEquals(WebUI.getElementText(headerCustomerPage), "Customers Summary", "The cusomer header page not match");
    }

    public void clickButtonAddNewCustomer() {
        WebUI.clickElement(buttonAddNewCustomer);
    }

    public void submitDataForNewCustomer(int row) {
        WebUI.waitForPageLoaded();

        WebUI.setText(inputCompany, excelHelper.getCellData("Company", row));
        WebUI.setText(inputVAT, excelHelper.getCellData("VAT", row));
        WebUI.setText(inputPhoneNumber, excelHelper.getCellData("Phone", row));
        WebUI.setText(inputWebsite, excelHelper.getCellData("Website", row));
        WebUI.sleep(1);
        WebUI.scrollToElementAtTop(labelGroup);
        WebUI.clickElement(dropdownGroup);
        WebUI.setText(inputSearchGroup, excelHelper.getCellData("Groups", row));
        WebUI.clickElement(itemGroup(excelHelper.getCellData("Groups", row)));
        WebUI.clickElement(dropdownGroup);
        WebUI.sleep(1);
        WebUI.clickElement(dropdownLanguage);
        WebUI.clickElement(itemLanguage(excelHelper.getCellData("Language", row)));
        WebUI.setText(inputAddress, excelHelper.getCellData("Address", row));
        WebUI.setText(inputCity, excelHelper.getCellData("City", row));
        WebUI.setText(inputState, excelHelper.getCellData("State", row));
        WebUI.setText(inputZip, excelHelper.getCellData("Zip_Code", row));
        WebUI.sleep(1);
        WebUI.clickElement(dropdownCountry);
        WebUI.setText(inputSearchCountry, excelHelper.getCellData("Country", row));
        WebUI.clickElement(itemCountry(excelHelper.getCellData("Country", row)));
        WebUI.scrollToElementAtBottom(buttonSave);
        WebUI.clickElement(buttonSave);
        WebUI.sleep(1);
        //verify alert message
        WebUI.assertEquals(DriverManager.getDriver().findElement(alertAddSuccess).getText(), "Customer added successfully.", "The Customer add failed");
    }

    public void submitData_WithNullCompany(int row) {
        WebUI.waitForPageLoaded();

        WebUI.setText(inputVAT, excelHelper.getCellData("VAT", row));
        WebUI.setText(inputPhoneNumber, excelHelper.getCellData("Phone", row));
        WebUI.setText(inputWebsite, excelHelper.getCellData("Website", row));
        WebUI.sleep(1);
        WebUI.scrollToElementAtTop(labelGroup);
        WebUI.clickElement(dropdownGroup);
        WebUI.setText(inputSearchGroup, excelHelper.getCellData("Groups", row));
        WebUI.clickElement(itemGroup(excelHelper.getCellData("Groups", row)));
        WebUI.clickElement(dropdownGroup);
        WebUI.sleep(1);
//        WebUI.clickElement(dropdownCurrency);
//        WebUI.setText(inputSearchCurrency, excelHelper.getCellData("Currency", row));
//        WebUI.clickElement(itemCurrency(excelHelper.getCellData("Currency", row)));
//        WebUI.clickElement(dropdownLanguage);
//        WebUI.clickElement(itemLanguage(excelHelper.getCellData("Language", row)));
//        WebUI.setText(inputAddress, excelHelper.getCellData("Address", row));
//        WebUI.setText(inputCity, excelHelper.getCellData("City", row));
//        WebUI.setText(inputState, excelHelper.getCellData("State", row));
//        WebUI.setText(inputZip, excelHelper.getCellData("Zip_Code", row));
//        WebUI.sleep(1);
        WebUI.clickElement(dropdownCountry);
        WebUI.setText(inputSearchCountry, excelHelper.getCellData("Country", row));
        WebUI.clickElement(itemCountry(excelHelper.getCellData("Country", row)));
        WebUI.scrollToElementAtBottom(buttonSave);
        WebUI.clickElement(buttonSave);
        WebUI.sleep(1);
        WebUI.assertEquals(WebUI.getCurrentURL(), "https://crm.anhtester.com/admin/clients/client", "FAIL. Vẫn tạo Customer dù không điền Company");
        WebUI.sleep(1);
        WebUI.scrollToElementAtTop(labelCompany);
        WebUI.assertEquals(WebUI.getElementText(errorCompany), "This field is required.", "The error company not match");
    }

    public void verifyNavigateToCustomerDetailPage() {
        Assert.assertTrue(WebUI.checkElementExist(headerCustomerDetailPage), "The customer detail header page not dissplay.");
        WebUI.assertEquals(WebUI.getElementText(headerCustomerDetailPage), "Profile", "The cusomer detail header page not match");
    }

    public void verifyAddNewCustomerSuccess(int row) {
        //verify data
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputCompany).getAttribute("value"), excelHelper.getCellData("Company", row), "The Company Name not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputVAT).getAttribute("value"), excelHelper.getCellData("VAT", row), "The VAT value not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputPhoneNumber).getAttribute("value"), excelHelper.getCellData("Phone", row), "The Phone Number not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputWebsite).getAttribute("value"), excelHelper.getCellData("Website", row), "The Webside not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(dropdownGroup).getAttribute("title"), excelHelper.getCellData("Groups", row), "The Group not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(dropdownLanguage).getAttribute("title"), excelHelper.getCellData("Language", row), "The Language not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputAddress).getText(), excelHelper.getCellData("Address", row), "The Address not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputCity).getAttribute("value"), excelHelper.getCellData("City", row), "The City not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputState).getAttribute("value"), excelHelper.getCellData("State", row), "The State not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(inputZip).getAttribute("value"), excelHelper.getCellData("Zip_Code", row), "The Zip Code not match");
        WebUI.assertEquals(DriverManager.getDriver().findElement(dropdownCountry).getAttribute("title"), excelHelper.getCellData("Country", row), "The Country not match");
    }

    public void searchAndCheckCustomerInTable(int row) {
        clickMenuCustomer();
        WebUI.setText(inputSearchCustomer, excelHelper.getCellData("Company", row));
        WebUI.sleep(2);
        String customerNameInTable = WebUI.getElementText(itemCustomerFirst);
        WebUI.logConsole(customerNameInTable);
        WebUI.assertEquals(customerNameInTable, excelHelper.getCellData("Company", row), "The customer name in table not match");
    }

    public int getCustomersTotal() {
        String totalString = WebUI.getElementText(totalCustomers);
        WebUI.logConsole("Total Customers: " + totalCustomers);
        return Integer.parseInt(totalString);
    }

    public void setStatus(int row) {
        excelHelper.setCellData("Passed", "Status", row);
    }

//    public void getAndSetURLCustomer(int row) {
//        String url = WebUI.getCurrentURL();
//        WebUI.logConsole(url);
//        excelHelper.setCellData(url, "URL_Customer", row);
//    }

}
