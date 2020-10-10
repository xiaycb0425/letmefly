package com.kingstar.kafka.exception;


import com.kingstar.common.response.BaseResponse;
import com.kingstar.common.response.ResultCode;
import com.kingstar.common.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * @return BaseResponse @Author ldl @Description 请求参数不存在异常拦截 @Date 2019/12/5 @Param
   *     MissingServletRequestParameterException
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public BaseResponse handleError(MissingServletRequestParameterException e) {
    log.warn("请求参数不存在", e);
    String message = String.format("请求参数不存在: %s", e.getParameterName());
    return ResultUtil.resErrorResult(
        ResultCode.PARAM_VALID_ERROR.getErrCode(),
        message,
        ResultCode.PARAM_VALID_ERROR.getErrMsg());
  }

  /**
   * @return BaseResponse @Author ldl @Description 不合法的请求参数拦截 @Date 2019/12/5 @Param
   *     IllegalArgumentException
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public BaseResponse handleError(IllegalArgumentException e) {
    log.warn("不合法的请求参数", e);
    String message = String.format("不合法的请求参数: %s", e.getMessage());
    return ResultUtil.resErrorResult(
        ResultCode.PARAM_VALID_ERROR.getErrCode(),
        message,
        ResultCode.PARAM_VALID_ERROR.getErrMsg());
  }

  /**
   * @return BaseResponse @Author ldl @Description 参数未给出为null @Date 2019/12/5 @Param
   *     NullPointerException
   */
  @ExceptionHandler(NullPointerException.class)
  public BaseResponse handleError(NullPointerException e) {
    log.warn("参数未给出为null", e);
    String message = String.format("参数未给出,请给出: %s", e.getMessage());
    return ResultUtil.resErrorResult(
        ResultCode.PARAM_MISS.getErrCode(), message, ResultCode.PARAM_MISS.getErrMsg());
  }

  /**
   * @return BaseResponse @Author ldl @Description 方法参数类型不匹配异常拦截 @Date 2019/12/5 @Param
   *     MethodArgumentTypeMismatchException
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public BaseResponse handleError(MethodArgumentTypeMismatchException e) {
    log.warn("方法参数类型不匹配异常", e);
    String message = String.format("Method Argument Type Mismatch: %s", e.getName());
    return ResultUtil.resErrorResult(
        ResultCode.PARAM_VALID_ERROR.getErrCode(),
        message,
        ResultCode.PARAM_VALID_ERROR.getErrMsg());
  }

    /**
     * 我的异常
     * @param myException
     * @return
     */
    @ExceptionHandler(MyException.class)
    public BaseResponse handleError(MyException myException) {

//        String serviceId = (String) modelMap.getAttribute("serviceId");
//        log.warn(serviceId);
        log.warn("导入异常", myException);
        String message = String.format("my exception: %s", myException.getMessage());
        return ResultUtil.resErrorResult(
                ResultCode.PARAM_VALID_ERROR.getErrCode(),
                message,
                ResultCode.PARAM_VALID_ERROR.getErrMsg());
    }


  /**
   * 404处理
   *
   * @return
   */
  @Bean
  public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
    return (factory -> {
      ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
      factory.addErrorPages(error404Page);
    });
  }
}
