package com.cmcc.syw.utils;

import com.alibaba.fastjson.JSON;
import com.cmcc.syw.model.Student;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunyiwei on 2015/9/18.
 */
public class JsonToStudentMsgConverter implements HttpMessageConverter<Student> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return clazz.equals(Student.class) && supports(mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.equals(Student.class) && supports(mediaType);
    }

    private boolean supports(MediaType mt){
        if (mt == null || MediaType.ALL.equals(mt)) {
            return true;
        }

        List<MediaType> types = getSupportedMediaTypes();
        for (MediaType mediaType : types) {
            if(mediaType.includes(mt)){
                return true;
            }
        }

        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> mts = new LinkedList<MediaType>();
        mts.add(MediaType.TEXT_HTML);
        mts.add(MediaType.ALL);

        return mts;
    }

    @Override
    public Student read(Class<? extends Student> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String content = StreamUtils.copyToString(inputMessage.getBody(), Charset.forName("utf-8"));

        int index = 0;
        String[] results = new String[2];
        String[] comps = content.split("&");
        for (String comp : comps) {
            String[] tmps = comp.split("=");
            results[index++] = tmps[1];
        }

        return new Student(results[0], NumberUtils.toInt(results[1], 27));
    }

    @Override
    public void write(Student student, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        StreamUtils.copy(JSON.toJSONString(student), getContentTypeCharset(contentType),outputMessage.getBody());
    }

    private Charset getContentTypeCharset(MediaType contentType) {
        if (contentType != null && contentType.getCharSet() != null) {
            return contentType.getCharSet();
        }
        else {
            return Charset.forName("utf-8");
        }
    }
}
