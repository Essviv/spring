package com.cmcc.syw.cxf;

import org.apache.cxf.feature.Features;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by sunyiwei on 2016/9/28.
 */
@WebService
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public interface HelloWorld {
    String sayHi(@WebParam(name = "name") String name);
}
