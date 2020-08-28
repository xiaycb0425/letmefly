package com.kingstar.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author zyl520
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private String errMsg;

    private String errInfo;

    private T errData;

    private int errCode = ResultCode.SUCCESS.getErrCode();

}
