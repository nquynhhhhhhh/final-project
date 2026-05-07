package com.nhuquynh.common;

import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.keywords.WebUI;
import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.pages.CustomerPage;
import com.nhuquynh.pages.ProjectPage;
import com.nhuquynh.pages.TaskPage;
import org.openqa.selenium.By;

public class BasePage {
    protected ExcelHelper excelHelper;

    public BasePage() {
        excelHelper = new ExcelHelper();
    }

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuCustomers = By.xpath("//span[normalize-space()='Customers']");
    private By menuTasks = By.xpath("//span[normalize-space()='Tasks']");
    private By menuProjects = By.xpath("//span[normalize-space()='Projects']");

    public CustomerPage clickMenuCustomer(){
        WebUI.waitForElementVisible(menuCustomers);
        WebUI.clickElement(menuCustomers);
        return new CustomerPage();
    }

    public ProjectPage clickMenuProject(){
        WebUI.waitForElementVisible(menuProjects);
        WebUI.clickElement(menuProjects);
        return new ProjectPage();
    }

    public TaskPage clickMenuTask(){
        WebUI.waitForElementVisible(menuTasks);
        WebUI.clickElement(menuTasks);
        return new TaskPage();
    }
}
