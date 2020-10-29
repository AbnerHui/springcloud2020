package com.abner.springcloud.myhandler;

import com.abner.springcloud.entities.CommonResult;
import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * 自定义限流处理类
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----1");
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444,"按客戶自定义,global handlerException----2");
    }
}
