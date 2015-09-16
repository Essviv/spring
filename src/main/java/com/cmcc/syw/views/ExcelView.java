package com.cmcc.syw.views;

import com.cmcc.syw.model.Student;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by sunyiwei on 2015/9/16.
 */
public class ExcelView extends AbstractJExcelView {
    private String[] columnNames = new String[] {"姓名", "年龄"};

    private String[] dbColumnNames = new String[] { "name", "age"};

    private Integer[] columnWidths = new Integer[] { 20, 20};

    @Override
    protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutputStream os = null;
        try {

            String excelName = "用户信息.xls";
            // 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
            response.setContentType("application/msexcel");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(excelName, "UTF-8"));
            os = response.getOutputStream();
            // sheet名称
            String sheetName = "用户信息";

            // 全局设置
            WorkbookSettings setting = new WorkbookSettings();
            java.util.Locale locale = new java.util.Locale("zh", "CN");
            setting.setLocale(locale);
            setting.setEncoding("UTF-8");
            // 创建工作薄
            workbook = Workbook.createWorkbook(os); // 建立excel文件
            // 创建第一个工作表
            jxl.write.WritableSheet ws = workbook.createSheet(sheetName, 1); // sheet名称
            // 添加标题
            addColumNameToWsheet(ws);

            List<Student> list = (List<Student>)model.get("list");
            writeContext(ws, list);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 写入文件
            try {
                workbook.write();
                workbook.close();
                os.flush();
                os.close();
            } catch (WriteException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // 添加标题样式
    private void addColumNameToWsheet(jxl.write.WritableSheet wsheet)
            throws RowsExceededException, WriteException {

        // 设置excel标题
        jxl.write.WritableFont wfont = getFont();
        if (null == wfont) {
            wfont = new WritableFont(WritableFont.ARIAL,
                    WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD);

        }
        jxl.write.WritableCellFormat wcfFC = getFormat();
        if (null == wcfFC) {
            wcfFC = new jxl.write.WritableCellFormat(wfont);
            try {
                wcfFC.setWrap(true);// 自动换行
                wcfFC.setAlignment(Alignment.CENTRE);
                wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// 设置对齐方式
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }

        jxl.write.Label wlabel1 = null;
        String[] columNames = columnNames;
        if (null == columNames)
            return;
        int colSize = columNames.length;

        Integer[] colsWidth = columnWidths;
        if (null == colsWidth) {
            colsWidth = new Integer[colSize];
            for (int i = 0; i < colSize; i++) {
                colsWidth[i] = 20;
            }
        }

        int temp = 0;
        String colName = null;
        for (int i = 0; i < colSize; i++) {
            colName = columNames[i];
            if (null == colName || "".equals(colName))
                colName = "";
            wlabel1 = new jxl.write.Label(i, 0, colName, wcfFC);
            wsheet.addCell(wlabel1);
            temp = colsWidth[i].intValue();
            // 默认设置列宽
            temp = temp == 0 ? 20 : temp;
            wsheet.setColumnView(i, temp);
        }

    }


    private <T> void writeContext(WritableSheet wsheet, List<T> list) {
        int rows = list.size();
        jxl.write.Label wlabel = null;
        jxl.write.WritableCellFormat wcf = getFormat();
        int cols = dbColumnNames.length;
        String columnName = null;
        Object value = null;
        try {
            for (int i = 0; i < rows; i++) {
                T t = (T) list.get(i);
                for (int j = 0; j < cols; j++) {
                    columnName = dbColumnNames[j].toLowerCase();
                    value = PropertyUtils.getProperty(t, columnName);
                    wlabel = new jxl.write.Label(j, (i + 1), value + "", wcf);
                    wlabel = new jxl.write.Label(j, (i + 1), value + "");
                    wsheet.addCell(wlabel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // 设置格式
    private WritableCellFormat getFormat() {

        jxl.write.WritableFont wfont = getFont();
        jxl.write.WritableCellFormat wcfFC = new jxl.write.WritableCellFormat(
                wfont);
        try {
            wcfFC.setWrap(true);
            wcfFC.setAlignment(Alignment.CENTRE);
            wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return wcfFC;
    }

    // 设置字体
    private WritableFont getFont() {
        return new WritableFont(WritableFont.ARIAL,
                WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD);
    }
}
