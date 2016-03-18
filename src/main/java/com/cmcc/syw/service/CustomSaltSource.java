package com.cmcc.syw.service;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by sunyiwei on 2016/1/7.
 */
public class CustomSaltSource implements SaltSource {
    @Override
    public Object getSalt(UserDetails user) {
        if(user instanceof  CustomUserDetails){
            CustomUserDetails customUserDetails = (CustomUserDetails)user;
            return customUserDetails.getSalt();
        }

        return null;
    }
}
