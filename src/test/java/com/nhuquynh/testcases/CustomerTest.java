package com.nhuquynh.testcases;

import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.pages.CustomerPage;
import com.nhuquynh.pages.DashboardPage;
import com.nhuquynh.pages.LoginPage;
import com.nhuquynh.common.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;

    @BeforeMethod
    public void getExcelData() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Customer");
    }

    @Test
    public void testAddNewCustomer() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();
        customerPage.verifyNavigateToCustomerPage();
        int berofeTotal = customerPage.getCustomersTotal();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitDataForNewCustomer(1);
        customerPage.verifyNavigateToCustomerDetailPage();
        customerPage.verifyAddNewCustomerSuccess(1);
        customerPage.clickMenuCustomer();

        int afterTotal = customerPage.getCustomersTotal();
        Assert.assertEquals(afterTotal, berofeTotal + 1, "The total customer beforeTotal and afterTotal not match ");
        customerPage.setStatus(1);
    }

    @Test
    public void testAddCustomer_WithNullCompany(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        customerPage = dashboardPage.clickMenuCustomer();
        customerPage.verifyNavigateToCustomerPage();
        customerPage.clickButtonAddNewCustomer();
        customerPage.submitData_WithNullCompany(3);
    }


}
