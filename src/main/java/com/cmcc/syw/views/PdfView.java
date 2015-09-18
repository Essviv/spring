package com.cmcc.syw.views;

import com.alibaba.fastjson.JSON;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Set;

/**
 * Created by sunyiwei on 2015/9/16.
 */
public class PdfView extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model,
                                    Document document,
                                    PdfWriter writer,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        response.setHeader("Content-disposition", "attachment;filename=" + "test.pdf");

        Set<String> keySet = model.keySet();
        for (String s : keySet) {
            document.add(new Paragraph(s + ": " + JSON.toJSONString(model.get(s))));
        }
    }
}