package com.educastro.sales.view;

import com.educastro.sales.model.Customer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import java.util.List;
import java.util.Map;

public class CustomerDataExcelExport extends AbstractXlsxView {

    @Override
    protected void buildExcelDocument(Map<String, Object> model,
                                      Workbook workbook,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        // define excel file name to be exported
        response.addHeader("Content-Disposition", "attachment;fileName=CustomerData.xlsx");

        // read data provided by controller
        @SuppressWarnings("unchecked")
        List<Customer> list = (List<Customer>) model.get("list");
        // create one sheet
        Sheet sheet = workbook.createSheet("Customer");
        // create row0 as a header
        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue("ID");
        row0.createCell(1).setCellValue("NAME");
        row0.createCell(2).setCellValue("LAST_NAME");
        row0.createCell(3).setCellValue("ADDRESS");
        row0.createCell(4).setCellValue("PHONE");

        // create row1 onwards from List<T>
        int rowNum = 1;
        for(Customer customer : list) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(customer.getIdCustomer());
            row.createCell(1).setCellValue(customer.getName());
            row.createCell(2).setCellValue(customer.getLastName());
            row.createCell(3).setCellValue(customer.getAddress());
            row.createCell(4).setCellValue(customer.getPhone());
        }
    }
}
