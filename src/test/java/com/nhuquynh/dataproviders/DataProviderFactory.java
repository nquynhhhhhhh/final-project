package com.nhuquynh.dataproviders;

import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.PropertiesHelper;
import com.nhuquynh.helpers.SystemHelper;
import com.nhuquynh.utils.LogUtils;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.DataProvider;

import java.util.*;

public class DataProviderFactory {

    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] loginData = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH"), "Login");
        LogUtils.info("Login Data from Excel: " + loginData);
        return loginData;
    }




}
