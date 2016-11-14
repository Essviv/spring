package com.cmcc.syw.enums;

/**
 * 多线程情况下enum.values返回的对象是否有影响
 * <p/>
 * Created by sunyiwei on 2016/11/11.
 */
public class EnumTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ChargeRecordStatus crs = ChargeRecordStatus.fromValue(3);
                System.out.println("Message: " + crs.getMessage());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ChargeRecordStatus crs = ChargeRecordStatus.fromValue(3);
                System.out.println("Message: " + crs.getMessage());

                //这里更改了枚举类型的错误信息，会对所有的枚举类型产生影响
                ChargeRecordStatus another_crs = ChargeRecordStatus.COMPLETE;
                crs.setMessage("这本来应该是充值成功，但我现在想改改它.");
                System.out.println("Message: " + another_crs.getMessage());

                ChargeRecordStatus third_crs = ChargeRecordStatus.fromValue(3);
                System.out.println("Message: " + third_crs.getMessage());

                assert crs == another_crs;
                assert crs == third_crs;
            }
        });

        t2.start();
        t2.join();

//        t1.start();
    }
}
