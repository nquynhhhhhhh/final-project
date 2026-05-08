package com.nhuquynh.dataproviders;

import com.nhuquynh.helpers.ExcelHelper;
import com.nhuquynh.helpers.SystemHelper;
import com.nhuquynh.utils.LogUtils;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.DataProvider;

import java.util.*;

public class DataProviderFactory {
    String pathExcel = "src/test/resources/dataTest/dataProjectCuoiKhoa.xlsx";
    
    @DataProvider(name = "data_provider_login_excel")
    public Object[][] dataLoginFromExcel() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] loginData = excelHelper.getExcelDataProvider(SystemHelper.getCurrentDir() + "src/test/resources/dataTest/dataProjectCuoiKhoa.xlsx", "Login");
        LogUtils.info("Login Data from Excel: " + loginData);
        return loginData;
    }

//    @DataProvider(name = "data_provider_project_customer_dataprovider")
//    public Object[][] dataProjectAndCustomer(ProjectDTO projectDTO) {
//        ExcelHelper excelHelper = new ExcelHelper();
//        // 1. Lấy danh sách Company hợp lệ từ sheet Customer
//        Object[][] customerData = excelHelper.getExcelDataProvider(pathExcel, "Customer");
//        Set<String> validCompany = new HashSet<>();
//        for (Object[] row : customerData) {
//            if (row[0] != null) {
//                validCompany.add(row[0].toString().trim());
//            }
//        }
//
//        // 2. Đọc dữ liệu Project
//        Object[][] projectData = excelHelper.getExcelDataProvider(pathExcel, "Project");
//        List<Object[]> resultList = new ArrayList<>();
//
//        for (int i = 0; i < projectData.length; i++) {
//            // Lấy giá trị cột Customer ở Index 1 trong sheet Project
//            String customerInProject = projectData[i][1].toString().trim();
//
//            //3. SO SÁNH: Chỉ nếu tên Customer này tồn tại trong Set Company
//            if (validCompany.contains(customerInProject)) {
//                // Thêm nguyên hàng 10 cột vào danh sách chạy Test
//                resultList.add(projectData[i]);
//            }
//        }
//        // Chuyển từ List sang Object[][] để trả về cho DataProvider
//        return resultList.toArray(new Object[0][]);
//    }


}
