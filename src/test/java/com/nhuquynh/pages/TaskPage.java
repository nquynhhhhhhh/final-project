package com.nhuquynh.pages;

import com.nhuquynh.common.BasePage;
import com.nhuquynh.drivers.DriverManager;
import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.helpers.SystemHelper;
import com.nhuquynh.keywords.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class TaskPage extends BasePage {
    public TaskPage() {
        super(); // Gọi constructor của BasePage để khởi tạo excelHelper
        excelHelper.setExcelFile(PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Task");
    }

//PROJECT PAGE (ADD TASK)
    private By buttonAddTaskOnProjectPage = By.xpath("//a[normalize-space()='New Task']");
    private By overviewProjectNumber = By.xpath("//div[contains(@class,'project-overview-id')]/dd");
//TASK PAGE
    private By headerTaskPage = By.xpath("//span[normalize-space()='Tasks Summary']");
    private By buttonAddNewTask = By.xpath("//a[normalize-space()='New Task']");
    private By inputSearchTask = By.xpath("//div[@id='tasks_filter']//input");
    private By itemTaskFirstOnTable(String projectName) {
        By xpathTaskFirstOnTable = By.xpath("//tbody/tr[1]//a[contains(text(),'"+ projectName +"')]/preceding-sibling::a");
        return xpathTaskFirstOnTable;
    }
//ADD TASK
    private By headerAddNewTask = By.xpath("//h4[@id='myModalLabel']");
    private By inputPublic = By.xpath("//input[@id='task_is_public'");
    private By checkboxVisibleToCustomer = By.xpath("//label[normalize-space()='Visible to customer']");
    private By attachFiles = By.xpath("//a[normalize-space()='Attach Files']");
    private By inputFiles = By.xpath("//input[@type='file']");
    private By inputSubject = By.xpath("//div[@role='document']//input[@id='name']");
    private By labelHourlyRate = By.xpath("//label[normalize-space()='Hourly Rate']");
    private By inputHourlyRate = By.xpath("//input[@id='hourly_rate']");
    private By labelMilestone = By.xpath("//select[@id='milestone']/parent::div/preceding-sibling::label[normalize-space()='Milestone']");
    private By inputStartDate = By.xpath("//input[@id='startdate']");
    private By inputDueDate = By.xpath("//input[@id='duedate']");
    private By dropdownPriority = By.xpath("//button[@data-id='priority']");
    private By itemPriority(String itemPriority) {
        By xpathPriority = By.xpath("//button[@data-id='priority']//following-sibling::div//li//span[normalize-space()='" + itemPriority + "']");
        return xpathPriority;
    }
    private By dropdownRepeatEvery = By.xpath("//button[@data-id='repeat_every']");
    private By itemRepeatEvery(String itemTimes) {
        By xpathRepeat = By.xpath("//button[@data-id='repeat_every']//following-sibling::div//li//span[normalize-space()='" + itemTimes + "']");
        return xpathRepeat;
    }
    private By labelTotalCycles = By.xpath("//label[normalize-space()='Total Cycles']");
    private By dropdownRelatedTo = By.xpath("//button[@data-id='rel_type']");
    private By itemRelatesTo(String itemRelatedTo) {
        By xpathRelatedTo = By.xpath("//button[@data-id='rel_type']/following-sibling::div//li//span[normalize-space()='"+ itemRelatedTo +"']");
        return xpathRelatedTo;
    }
    private By dropdownProject = By.xpath("//button[@data-id='rel_id']");
    private By inputSearchProject = By.xpath("//button[@data-id='rel_id']/following-sibling::div//input");
    private By itemProject(String itemProjectNumber, String itemProject){
        By xpathProject = By.xpath("//button[@data-id='rel_id']/following-sibling::div//span[contains(.,'"+ itemProjectNumber +"') and contains(text(),'"+ itemProject +"')]");
        return xpathProject;
    }
    private By dropdownAssignees = By.xpath("//button[@data-id='assignees']");
    private By itemAssignees(String itemAssignees){
        By xpathAssignees = By.xpath("//button[@data-id='assignees']/following-sibling::div//span[normalize-space()='"+ itemAssignees +"']");
        return xpathAssignees;
    }
    private By dropdowmFollower = By.xpath("//button[@data-id='followers[]']");
    private By itemFollower(String itemFollower){
        By xpathFollower = By.xpath("//button[@data-id='followers[]']/following-sibling::div//li//span[normalize-space()='"+ itemFollower +"']");
        return xpathFollower;
    }
    private By inputTags = By.xpath("//div[@id='inputTagsWrapper']//input[@placeholder='Tag']");
    private By textareaTaskDescription = By.xpath("//div[@class='form-group no-mbot']//textarea[@id='description']");
    private By inputTaskDescription = By.xpath("//body[@id='tinymce']");
    private By buttonSave = By.xpath("//div[@role='document']//button[normalize-space()='Save']");
//VERIFY SAU KHI ADD
    private By headerTask(String title){
        By xpathHeader = By.xpath("//h4[contains(text(),'"+ title +"')]");
        return xpathHeader;
    }



//===========================HÀM XỬ LÝ==================================
    public void clickAddTask() {
        WebUI.clickElement(buttonAddNewTask);
    }

    public void verifyAddNewTaskDisplayed() {
        Assert.assertTrue(WebUI.checkElementExist(headerAddNewTask, 5, 500), "Add New Task dialog is not displayed.");
        Assert.assertEquals(WebUI.getElementText(headerAddNewTask), "Add new task", "The header Add new task does not match.");
    }

    public void addTask_ProjectPage(int row){
        WebUI.waitForPageLoaded();

        WebUI.isElementDisplayed(checkboxVisibleToCustomer);
        WebUI.clickElement(attachFiles);
        WebUI.sleep(1);
        String filepath = SystemHelper.getCurrentDir() + "src\\test\\resources\\dataTest\\schedule.jpg";
        WebUI.setText(inputFiles,filepath);

        WebUI.sleep(1);
        WebUI.setText(inputSubject, excelHelper.getCellData("Subject", row));
        WebUI.clearText(inputHourlyRate);
        WebUI.setText(inputHourlyRate, excelHelper.getCellData("Hourly_Rate", row));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        WebElement element = DriverManager.getDriver().findElement(labelMilestone);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        WebUI.sleep(1);
        WebUI.clearText(inputStartDate);
        WebUI.setText(inputStartDate,excelHelper.getCellData("Start_Date",row));
        WebUI.clearText(inputDueDate);
        WebUI.setText(inputDueDate,excelHelper.getCellData("Due_Date",row));
        WebUI.sleep(1);
        WebUI.clickElement(dropdownPriority);
        WebUI.clickElement(itemPriority(excelHelper.getCellData("Priority",row)));
        WebUI.clickElement(dropdownRepeatEvery);
        WebUI.clickElement(itemRepeatEvery(excelHelper.getCellData("Repeat _Every",row)));
        WebUI.isElementDisplayed(labelTotalCycles);
        WebUI.clickElement(dropdownAssignees);
        WebUI.sleep(1);
        WebUI.clickElement(itemAssignees(excelHelper.getCellData("Assignees",row)));
        WebUI.clickElement(dropdownAssignees);
        WebUI.clickElement(dropdowmFollower);
        WebUI.clickElement(itemFollower(excelHelper.getCellData("Followers",row)));
        WebUI.setText(inputTags,excelHelper.getCellData("Tags",row));
        WebUI.sleep(1);
        WebUI.clickElement(textareaTaskDescription);
        WebUI.sleep(1);
        WebUI.setTextOnFrameDescription(inputTaskDescription,excelHelper.getCellData("Task Description",row));
        WebUI.scrollToElementAtBottom(buttonSave);
        WebUI.clickElement(buttonSave);
        WebUI.sleep(1);
    }

    public void addTask_TaskPage(int row){
        WebUI.waitForPageLoaded();

        WebUI.isElementNotDisplayed(checkboxVisibleToCustomer);
        WebUI.clickElement(attachFiles);
        WebUI.sleep(1);
        String filepath = SystemHelper.getCurrentDir() + "src\\test\\resources\\dataTest\\schedule.jpg";
        WebUI.setText(inputFiles,filepath);

        WebUI.sleep(1);
        WebUI.setText(inputSubject, excelHelper.getCellData("Subject", row));
        WebUI.clearText(inputHourlyRate);
        WebUI.setText(inputHourlyRate, excelHelper.getCellData("Hourly_Rate", row));
        WebUI.scrollToElementAtTop(labelHourlyRate);
        WebUI.sleep(1);
        WebUI.clearText(inputStartDate);
        WebUI.setText(inputStartDate,excelHelper.getCellData("Start_Date",row));
        WebUI.clearText(inputDueDate);
        WebUI.setText(inputDueDate,excelHelper.getCellData("Due_Date",row));
        WebUI.sleep(1);
        WebUI.clickElement(dropdownPriority);
        WebUI.clickElement(itemPriority(excelHelper.getCellData("Priority",row)));
        WebUI.clickElement(dropdownRepeatEvery);
        WebUI.clickElement(itemRepeatEvery(excelHelper.getCellData("Repeat _Every",row)));
        WebUI.isElementDisplayed(labelTotalCycles);
        WebUI.sleep(1);
        WebUI.clickElement(dropdownRelatedTo);
        WebUI.clickElement(itemRelatesTo(excelHelper.getCellData("Related_To",row)));
        WebUI.clickElement(dropdownProject);
        WebUI.setText(inputSearchProject,excelHelper.getCellData("Project",row));
        WebUI.waitForElementVisible(itemProject(excelHelper.getCellData("Project_Number",row),excelHelper.getCellData("Project",row)));
        WebUI.clickElement(itemProject(excelHelper.getCellData("Project_Number",row),excelHelper.getCellData("Project",row)));
        WebUI.sleep(1);
        WebUI.clickElement(dropdownAssignees);
        WebUI.sleep(1);
        WebUI.clickElement(itemAssignees(excelHelper.getCellData("Assignees",row)));
        WebUI.clickElement(dropdownAssignees);
        WebUI.clickElement(dropdowmFollower);
        WebUI.clickElement(itemFollower(excelHelper.getCellData("Followers",row)));
        WebUI.sleep(1);
        WebUI.setText(inputTags,excelHelper.getCellData("Tags",row));
        WebUI.sleep(1);
        WebUI.clickElement(textareaTaskDescription);
        WebUI.sleep(1);
        WebUI.setTextOnFrameDescription(inputTaskDescription,excelHelper.getCellData("Task Description",row));
        WebUI.scrollToElementAtBottom(buttonSave);
        WebUI.clickElement(buttonSave);
        WebUI.sleep(1);
    }

    public void verifyAddTaskSuccess(){

    }

    public void searchTask(int row) {
        WebUI.waitForPageLoaded();

        WebUI.setTextAndKey(inputSearchTask, excelHelper.getCellData("Subject",row), Keys.ENTER);
        WebUI.sleep(1);
        WebUI.waitForPageLoaded();

        WebUI.getElementText(itemTaskFirstOnTable(excelHelper.getCellData("Project",row)));



    }


}




