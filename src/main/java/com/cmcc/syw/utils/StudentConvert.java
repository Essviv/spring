package com.cmcc.syw.utils;

import com.cmcc.syw.model.Student;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by sunyiwei on 2015/9/2.
 */
public class StudentConvert implements Converter<String, Student> {
    @Override
    public Student convert(String source) {
        return new Student(source, 27);
    }
}
