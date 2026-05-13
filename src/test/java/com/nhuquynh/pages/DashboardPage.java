package com.nhuquynh.pages;

import com.nhuquynh.common.BasePage;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage extends BasePage {
    private By titleInvoicesAwaitingPayment = By.xpath("//span[normalize-space()='Invoices Awaiting Payment']");
    private By totalInvoicesAwaitingPayment = By.xpath("(//span[normalize-space()='Invoices Awaiting Payment']/parent::div)/following-sibling::span");

    private By titleConvertedLeads = By.xpath("//span[normalize-space()='Converted Leads']");
    private By totalConvertedLeads = By.xpath("(//span[normalize-space()='Converted Leads']/parent::div)/following-sibling::span");

    private By titleProjectsInProgress = By.xpath("//span[normalize-space()='Projects In Progress']");
    private By totalProjectInProgress = By.xpath("(//span[normalize-space()='Projects In Progress']/parent::div)/following-sibling::span");

    private By titleTasksNotFinished = By.xpath("//span[normalize-space()='Tasks Not Finished']");
    private By totalTasksNotFinished = By.xpath("(//span[normalize-space()='Tasks Not Finished']/parent::div)/following-sibling::span");

    public void verifyInvoicesAwaitingPayment() {
        Assert.assertEquals(WebUI.getElementText(titleInvoicesAwaitingPayment), "Invoices Awaiting Payment", "The Invoices Awaiting Payment title not match");
        Assert.assertTrue(WebUI.isElementDisplayed(totalInvoicesAwaitingPayment), "The Invoices Awaiting Payment total label not match");
        WebUI.getElementText(totalInvoicesAwaitingPayment);
    }

    public void verifyConvertedLeads() {
        Assert.assertEquals(WebUI.getElementText(titleConvertedLeads), "Converted Leads", "The Converted Leads title not match");
        Assert.assertTrue(WebUI.isElementDisplayed(totalConvertedLeads), "The Converted Leads total label not match");
        WebUI.getElementText(totalConvertedLeads);
    }

    public void verifyProjectsInProgress() {
        Assert.assertEquals(WebUI.getElementText(titleProjectsInProgress), "Projects In Progress", "The Projects In Progress title not match");
        Assert.assertTrue(WebUI.isElementDisplayed(totalProjectInProgress), "The Projects In Progress total label not match");
        WebUI.getElementText(totalProjectInProgress);
    }

    public void verifyTasksNotFinished() {
        Assert.assertEquals(WebUI.getElementText(titleTasksNotFinished), "Tasks Not Finished", "The Tasks Not Finished title not match");
        Assert.assertTrue(WebUI.isElementDisplayed(totalTasksNotFinished), "The Tasks Not Finished total label not match");
        WebUI.getElementText(totalTasksNotFinished);
    }



}
