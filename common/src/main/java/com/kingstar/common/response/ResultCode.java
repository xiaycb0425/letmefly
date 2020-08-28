package com.kingstar.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @author zyl520
 */

@Getter
@AllArgsConstructor
public enum  ResultCode {

    SUCCESS(4000, "Operation is Successful","success"),

    FAILURE(4001, "Biz Exception","业务处理错误"),

    UN_AUTHORIZED(4002, "Request Unauthorized","请求未经授权"),

    NOT_FOUND(4003, "404 Not Found","未找到方法"),

    MSG_NOT_READABLE(4004, "Message Can't be Read","信息不能被读"),

    METHOD_NOT_SUPPORTED(4005, "Method Not Supported","方法不支持"),

    MEDIA_TYPE_NOT_SUPPORTED(4006, "Media Type Not Supported","不支持媒体类型"),

    REQ_REJECT(4007, "Request Rejected","请求被拒绝"),

    INTERNAL_SERVER_ERROR(4008, "Internal Server Error","内部服务器错误"),

    PARAM_MISS(4009, "Missing Required Parameter","请求参数不存在"),

    PARAM_TYPE_ERROR(4010, "Parameter Type Mismatch","参数类型不匹配"),

    PARAM_BIND_ERROR(4011, "Parameter Binding Error","参数绑定错误"),

    PARAM_VALID_ERROR(4012, "Parameter Validation Error","参数验证错误"),

    DATA_OPERATION_ERROR(4013,"data operation Error","数据处理失败"),

    JWT_TOKEN_VERIFY_FAIL(4014, "Token Verify Fail", "token验证失败"),

    JWT_TOKEN_VERIFY_ERROR(4015, "Token Verify Error", "token验证过程异常，请联系技术人员"),

    DATABASE_ERROR(4016, "DataBase Error", "数据库异常"),

    EXCEL_EXPORT_ERROR(4017, "excel export Error", "excel文件导出异常");

    final int errCode;

    final String errInfo;

    final String errMsg;


}
