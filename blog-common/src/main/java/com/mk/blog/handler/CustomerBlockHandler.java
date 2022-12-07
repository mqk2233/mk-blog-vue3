package com.mk.blog.handler;

import com.mk.blog.http.ResponseEntity;
import com.sun.deploy.security.BlockedException;

/**
 * @author MK
 * @date 2021/7/25
 */
public class CustomerBlockHandler {

    public static ResponseEntity<?> handlerException(BlockedException exception){
        return ResponseEntity.buildError();
    }
}
