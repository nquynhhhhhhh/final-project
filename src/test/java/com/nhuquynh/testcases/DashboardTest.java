package com.nhuquynh.testcases;

import com.nhuquynh.pages.DashboardPage;
import com.nhuquynh.pages.LoginPage;
import com.nhuquynh.common.BaseTest;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Test
    public void checkDashboardTotal() {
        loginPage = new LoginPage();

        dashboardPage = loginPage.loginCRM();
        dashboardPage.verifyInvoicesAwaitingPayment();
        dashboardPage.verifyConvertedLeads();
        dashboardPage.verifyProjectsInProgress();
        dashboardPage.verifyTasksNotFinished();
    }
}
