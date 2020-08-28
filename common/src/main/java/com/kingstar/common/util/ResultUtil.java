package com.kingstar.common.util;

import com.kingstar.common.response.BaseResponse;
import com.kingstar.common.response.ResultCode;

/**
 * @author zyl520
 */
public class ResultUtil {

    /**
     * @Author ldl
     * @Description 封裝成功返回結果
     * @Date 2019/12/5
     * @param t
     * @return BaseResponse
     **/
    public static  <T> BaseResponse<T> resSuccessResult(T t){
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setErrCode(ResultCode.SUCCESS.getErrCode());
        baseResponse.setErrInfo(ResultCode.SUCCESS.getErrMsg());
        baseResponse.setErrMsg(ResultCode.SUCCESS.getErrMsg());
        baseResponse.setErrData(t);
        return baseResponse;
    }


    /**
     * @Author ldl
     * @Description 封裝成功返回結果
     * @Date
     * @Param
     * @return $return
     **/
    public static  <T> BaseResponse<T> resSuccessResult(T t,int errCode,String errInfo,String errMsg){
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setErrCode(errCode);
        baseResponse.setErrInfo(errInfo);
        baseResponse.setErrMsg(errMsg);
        baseResponse.setErrData(t);
        return baseResponse;
    }

    /**
     * @Author ldl
     * @Description 封裝失败返回結果
     * @Date 2019/12/5
     * @param errCode
     * @param errInfo
     * @param errMsg
     * @return BaseResponse
     **/
    public static BaseResponse<String> resErrorResult(int errCode,String errInfo,String errMsg){
        BaseResponse<String> baseResponse = new BaseResponse<String>();
        baseResponse.setErrCode(errCode);
        baseResponse.setErrInfo(errInfo);
        baseResponse.setErrMsg(errMsg);
        return baseResponse;
    }


    /**
     * @Author ldl
     * @Description 封裝失败返回結果-数据处理失败结果
     * @Date 2019/12/5
     * @param message
     * @return BaseResponse
     **/
    public static BaseResponse<String> resErrorResult(String message){
        BaseResponse<String> baseResponse = new BaseResponse<String>();
        baseResponse.setErrCode(ResultCode.DATA_OPERATION_ERROR.getErrCode());
        baseResponse.setErrInfo(message);
        baseResponse.setErrMsg(message);
        return baseResponse;
    }

    /**
     * @Author ldl
     * @Description 封裝失败返回結果
     * @Date 2019/12/5
     * @param errCode
     * @param errInfo
     * @param errMsg
     * @param t
     * @return BaseResponse
     **/
    public static <T> BaseResponse<T> resErrorResult(T t,int errCode, String errInfo, String errMsg){
        BaseResponse<T> baseResponse = new BaseResponse<T>();
        baseResponse.setErrData(t);
        baseResponse.setErrCode(errCode);
        baseResponse.setErrInfo(errInfo);
        baseResponse.setErrMsg(errMsg);
        return baseResponse;
    }

}
