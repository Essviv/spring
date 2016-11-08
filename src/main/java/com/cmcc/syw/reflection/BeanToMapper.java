package com.cmcc.syw.reflection;

import com.cmcc.syw.annotations.LogicalDeleteFlag;
import com.cmcc.syw.annotations.Primary;
import com.cmcc.syw.model.Model;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Text;
import org.dom4j.dom.DOMDocumentType;
import org.dom4j.dom.DOMElement;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * 根据bean类型自动生成mapper文件 <p/> Created by sunyiwei on 2016/11/7.
 */
public class BeanToMapper {
    private Field primaryFiled;
    private Field logicalDeleteFlagField;
    private Class clazz;

    public BeanToMapper(Class clazz) {
        this.clazz = clazz;

        init();
    }

    public static void main(String[] args) {
        System.out.println(new BeanToMapper(Model.class).toMapper());
    }

    private void init() {
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            if (f.getAnnotation(Primary.class) != null) {
                this.primaryFiled = f;
            } else if (f.getAnnotation(LogicalDeleteFlag.class) != null) {
                this.logicalDeleteFlagField = f;
            }
        }

        if (primaryFiled == null) {
            throw new RuntimeException("PrimaryField是必须的...");
        }
    }

    public String toMapper() {
        Document document = DocumentHelper.createDocument();

        //doctype
        document.setDocType(new DOMDocumentType("mapper", "-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd"));

        //append root
        Element root = appendRoot(document);

        //append resultMap
        appendResultmap(root);

        //append sql
        appendSql(root);

        //append get
        appendGet(root);

        //append delete
        appendDelete(root);

        //append create
        appendCreate(root);

        return document.asXML();
    }

    private void appendGet(Element root) {
        Element get = root.addElement("select");
        get.addAttribute("id", "get");
        get.addAttribute("resultMap", "BaseResultMap");

        String getText = buildGetStatement();
        get.addText(getText);
    }

    private String buildGetStatement() {
        StringBuilder stringBuilder = new StringBuilder();

        Element element = new DOMElement("include");
        element.addAttribute("refid", "Base_Column_List");

        stringBuilder.append("select ").append(element.asXML())
                .append(" from ").append(clazz.getSimpleName())
                .append(buildWhere());

        return stringBuilder.toString();
    }

    private String buildWhere() {
        return " where "
                + primaryFiled.getName()
                + "=#{" + primaryFiled.getName()
                + " ,jdbcType = }";
    }

    private void appendDelete(Element root) {
        if (this.logicalDeleteFlagField == null) {
            return;
        }

        Element delete = root.addElement("update");
        delete.addAttribute("id", "delete");
        delete.addAttribute("parameterType", clazz.getName());

        String text = buildDeleteStatement();
        delete.addText(text);
    }

    private String buildDeleteStatement() {
        return "update " +
                clazz.getSimpleName() +
                " set " + logicalDeleteFlagField.getName() +
                "=`true`" + buildWhere();
    }

    private void appendCreate(Element root) {
        Element create = root.addElement("insert");
        create.addAttribute("id", "insert");
        create.addAttribute("parameterType", clazz.getName());
        create.addAttribute("useGeneratedKeysr", "true");
        create.addAttribute("keyProperty", primaryFiled.getName());

        String insertText = buildInsertStatement();
        create.addText(insertText);
    }

    private String buildInsertStatement() {
        StringBuilder sb = new StringBuilder();

        sb.append("insert into ").append(clazz.getSimpleName())
                .append("(");
        Field[] fs = clazz.getDeclaredFields();
        int length = fs.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(fs[i].getName());
        }
        sb.append(")values(");

        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }

            sb.append("#{")
                    .append(fs[i].getName())
                    .append(", jdbcType=")
                    .append(convert(fs[i].getType()))
                    .append("}");
        }

        sb.append(")");
        return sb.toString();
    }

    private Element appendRoot(Document document) {
        Element root = document.addElement("mapper");
        root.addAttribute("namespace", clazz.getName());

        return root;
    }

    private void appendSql(Element root) {
        Field[] fields = clazz.getDeclaredFields();
        Element element = root.addElement("sql");
        element.addAttribute("id", "Base_Column_List");

        StringBuilder sb = new StringBuilder();
        int length = fields.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(fields[i].getName());
        }

        element.addText(sb.toString());
    }

    private void appendResultmap(Element root) {
        Element resultMapElem = root.addElement("resultMap");
        resultMapElem.addAttribute("id", "BaseResultMap");
        resultMapElem.addAttribute("type", clazz.getName());

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Element resultElem = resultMapElem.addElement("result");
            resultElem.addAttribute("column", field.getName());
            resultElem.addAttribute("property", field.getName());
            resultElem.addAttribute("jdbcType", convert(field.getType()));
        }
    }

    private String convert(Class fieldType) {
        if(fieldType == String.class){
            return "VARCHAR";
        }else if(fieldType == int.class || fieldType == Integer.class){
            return "INTEGER";
        }else if(fieldType == boolean.class || fieldType == Boolean.class){
            return "BOOLEAN";
        }else if(fieldType == Date.class){
            return "DATETIME";
        }

        return null;
    }
}
