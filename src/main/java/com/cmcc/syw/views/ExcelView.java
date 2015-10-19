package com.cmcc.syw.views;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by sunyiwei on 2015/9/16.
 */
public class ExcelView extends AbstractExcelView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HSSFSheet sheet;
        HSSFCell cell;
        sheet = workbook.createSheet("Spring");
        sheet.setDefaultColumnWidth(12);

        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + "test.xls");

        // write a text at A1
        cell = getCell(sheet, 0, 0);
        setText(cell, "Spring-Excel 测试");
    }
}
