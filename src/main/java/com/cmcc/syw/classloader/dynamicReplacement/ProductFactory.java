package com.cmcc.syw.classloader.dynamicReplacement;

import java.lang.ref.WeakReference;
import java.lang.reflect.Proxy;
import java.util.LinkedList;
import java.util.List;

/**
 * 动态替换Product接口
 *
 * Created by sunyiwei on 2016/11/14.
 */
public class ProductFactory {
    //当前的Product接口实现类
    private Class<? extends Product> implClass;

    //当前Product接口的所有实例
    private List<WeakReference<Product>> instances;

    public ProductFactory(Class<? extends Product> implClass) {
        this.implClass = implClass;
        this.instances = new LinkedList<>();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        //先用ProductImplA实现
        ProductFactory productFactory = new ProductFactory(ProductImplA.class);

        Product a = productFactory.newInstance();
        a.printName();

        //再用ProductImplB实现, 这里演示了动态替换
        productFactory.replace(ProductImplB.class);
        a.printName();

        //再用ProductImplC实现, 这里演示了动态替换
        productFactory.replace(ProductImplC.class);
        a.printName();
    }

    /**
     * 根据新的实现类获取新的实例
     */
    public Product newInstance() throws IllegalAccessException, InstantiationException {
        Class[] itfs = new Class[]{Product.class};

        //这里通过动态代理隔离了真实的Product实现与客户端, 使得动态替换变得可能.
        Product product = (Product) Proxy.newProxyInstance(ProductFactory.class.getClassLoader(),
                itfs, new ProductInvacationHandler(implClass.newInstance()));
       
        instances.add(new WeakReference<>(product));

        return product;
    }

    /**
     * 替换新的实现
     *
     * @param newImplClass 新的实现
     * @return 替换成功为true, 否则为false
     */
    public void replace(Class<? extends Product> newImplClass) throws IllegalAccessException, InstantiationException {
        implClass = newImplClass;

        for (WeakReference<Product> instance : instances) {
            Product proxy = instance.get();
            if (proxy == null) {    //弱引用,有可能已经被GC回收了
                continue;
            }

            ProductInvacationHandler pih = (ProductInvacationHandler) Proxy.getInvocationHandler(proxy);
            pih.setProduct(newImplClass.newInstance());
        }
    }
}
