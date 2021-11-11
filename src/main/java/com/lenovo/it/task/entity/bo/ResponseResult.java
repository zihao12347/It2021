package com.lenovo.it.task.entity.bo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 响应结果的封装类
 */
@Data
@ToString
public class ResponseResult {
    private String reresultCode;
    private String resultInfo;
    private Object result;

    public ResponseResult() {
    }

    public ResponseResult(String reresultCode, String resultInfo, Object result) {
        this.reresultCode = reresultCode;
        this.resultInfo = resultInfo;
        this.result = result;
    }
}
