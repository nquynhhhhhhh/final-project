package com.nhuquynh.testcases;

import com.nhuquynh.common.BaseTest;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.pages.CustomerPage;
import com.nhuquynh.pages.DashboardPage;
import com.nhuquynh.pages.LoginPage;
import com.nhuquynh.pages.ProjectPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;

    @BeforeMethod
    public void getExcelData() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Project");
    }

    @Test(priority = 1)
    public void testAddNewProject() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        projectPage = dashboardPage.clickMenuProject();
        projectPage.verifyNavigateToProjectPage();
        projectPage.clickButtonAddNewProject();
        projectPage.submitDataForNewProject(1);
        projectPage.verifyProjectProfile(1);
    }

    @Test(priority = 2)
    public void testEditProject() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        projectPage = dashboardPage.clickMenuProject();
        projectPage.verifyNavigateToProjectPage();
        projectPage.clickButtonAddNewProject();
        projectPage.submitDataForNewProject(2);
        projectPage.verifyProjectProfile(2);
        projectPage = dashboardPage.clickMenuProject();
        projectPage.searchProject(2);
        projectPage.editProject(3);
        projectPage.verifyProjectProfile(3);
    }

    @Test(priority = 3)
    public void testDeleteProject() {
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        projectPage = dashboardPage.clickMenuProject();
        projectPage.verifyNavigateToProjectPage();
        projectPage.clickButtonAddNewProject();
        projectPage.submitDataForNewProject(4);
        projectPage.verifyProjectProfile(4);
        projectPage = dashboardPage.clickMenuProject();
        projectPage.searchProject(4);
        projectPage.deleteProject(4);
    }


}
