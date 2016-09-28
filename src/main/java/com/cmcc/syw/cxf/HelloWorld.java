package com.cmcc.syw.cxf;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by sunyiwei on 2016/9/28.
 */
@WebService
public interface HelloWorld {
    String sayHi(@WebParam(name = "name") String name);
}
