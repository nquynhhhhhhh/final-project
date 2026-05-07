package com.nhuquynh.testcases;

import com.nhuquynh.common.BaseTest;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.pages.DashboardPage;
import com.nhuquynh.pages.LoginPage;
import com.nhuquynh.pages.ProjectPage;
import com.nhuquynh.pages.TaskPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TaskTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    ProjectPage projectPage;
    TaskPage taskPage;

    @BeforeMethod
    public void getExcelData(){
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"),"Task");
    }

    @Test
    public void testAddNewTask_FromProjectPage(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        projectPage = dashboardPage.clickMenuProject();
        projectPage.searchProject(1);
        projectPage.clickItemProject(1);
        projectPage.verifyProjectProfile(1);
        taskPage = projectPage.clickButtonAddTask();
        taskPage.verifyAddNewTaskDisplayed();
        taskPage.addTask_ProjectPage(1);

    }

    @Test
    public void testAddNewTask_FromTaskPage(){
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginCRM();
        taskPage = dashboardPage.clickMenuTask();
        taskPage.clickAddTask();
        taskPage.verifyAddNewTaskDisplayed();
        taskPage.addTask_TaskPage(2);
    }


}
