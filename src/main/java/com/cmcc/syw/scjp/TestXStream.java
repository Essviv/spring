package com.cmcc.syw.scjp;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.FieldDictionary;
import com.thoughtworks.xstream.converters.reflection.NativeFieldKeySorter;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;

/**
 * Created by sunyiwei on 2016/10/27.
 */
public class TestXStream {
    public static void main(String[] args) {
        XStream xStream = new XStream(new PureJavaReflectionProvider(new FieldDictionary(new NativeFieldKeySorter())));
        xStream.alias("A", A.class);
        xStream.autodetectAnnotations(true);

        A a = new A();
        a.setA("fdsaf");
        a.setOrder("fdsal");
        System.out.println(xStream.toXML(a));
    }
}
