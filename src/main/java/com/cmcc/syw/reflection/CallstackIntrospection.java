package com.cmcc.syw.reflection;

/**
 * 查看调用堆栈
 * <p/>
 * Created by sunyiwei on 2016/11/11.
 */
public class CallstackIntrospection {
    public static void main(String[] args) {
        CallstackIntrospection ci = new CallstackIntrospection();
        wrapper(ci);
    }

    private static void wrapper(CallstackIntrospection ci) {
        ci.inspect();
    }

    public void inspect() {
        Throwable throwable = new Throwable();

        StackTraceElement ste = throwable.getStackTrace()[1];
        System.out.println("ClassName = " + ste.getClassName());
        System.out.println("Method = " + ste.getMethodName());
        System.out.println("FileName = " + ste.getFileName());
        System.out.println("LineNumber = " + ste.getLineNumber());
        System.out.println("IsNative = " + ste.isNativeMethod());
    }
}
