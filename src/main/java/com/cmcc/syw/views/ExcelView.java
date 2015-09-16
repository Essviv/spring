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
    private String[] columnNames = new String[] {"����", "����"};

    private String[] dbColumnNames = new String[] { "name", "age"};

    private Integer[] columnWidths = new Integer[] { 20, 20};

    @Override
    protected void buildExcelDocument(Map<String, Object> model, WritableWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        OutputStream os = null;
        try {

            String excelName = "�û���Ϣ.xls";
            // ����response��ʽ,ʹִ�д�controllerʱ���Զ���������ҳ��,����ֱ��ʹ��excel��
            response.setContentType("application/msexcel");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(excelName, "UTF-8"));
            os = response.getOutputStream();
            // sheet����
            String sheetName = "�û���Ϣ";

            // ȫ������
            WorkbookSettings setting = new WorkbookSettings();
            java.util.Locale locale = new java.util.Locale("zh", "CN");
            setting.setLocale(locale);
            setting.setEncoding("UTF-8");
            // ����������
            workbook = Workbook.createWorkbook(os); // ����excel�ļ�
            // ������һ��������
            jxl.write.WritableSheet ws = workbook.createSheet(sheetName, 1); // sheet����
            // ��ӱ���
            addColumNameToWsheet(ws);

            List<Student> list = (List<Student>)model.get("list");
            writeContext(ws, list);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // д���ļ�
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

    // ��ӱ�����ʽ
    private void addColumNameToWsheet(jxl.write.WritableSheet wsheet)
            throws RowsExceededException, WriteException {

        // ����excel����
        jxl.write.WritableFont wfont = getFont();
        if (null == wfont) {
            wfont = new WritableFont(WritableFont.ARIAL,
                    WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD);

        }
        jxl.write.WritableCellFormat wcfFC = getFormat();
        if (null == wcfFC) {
            wcfFC = new jxl.write.WritableCellFormat(wfont);
            try {
                wcfFC.setWrap(true);// �Զ�����
                wcfFC.setAlignment(Alignment.CENTRE);
                wcfFC.setVerticalAlignment(VerticalAlignment.CENTRE);// ���ö��뷽ʽ
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
            // Ĭ�������п�
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

    // ���ø�ʽ
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

    // ��������
    private WritableFont getFont() {
        return new WritableFont(WritableFont.ARIAL,
                WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD);
    }
}
