package com.cmcc.syw.service.impl;

import com.cmcc.syw.service.SingletonService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by sunyiwei on 2016/6/17.
 */
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Service("singletonService")
public class SingletonServiceImpl implements SingletonService {
}
