package com.huatu.ic.search.request.mq;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jbzm
 * @date 20182:16 PM
 **/
@Getter
@Setter
public class BaseRequest {
    /**
     * 接收json数据
     */
    private String data;
    /**
     * 接收操作对象
     */
    private String operateType;
    /**
     * 接收具体操作
     */
    private String operation;
}