package com.cmcc.syw.classloader.dynamicReplacement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Product接口的代理
 *
 * Created by sunyiwei on 2016/11/14.
 */
public class ProductInvacationHandler implements InvocationHandler {
    private Product product;

    public ProductInvacationHandler(Product product) {
        this.product = product;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(product, args);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
