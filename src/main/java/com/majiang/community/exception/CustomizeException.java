package com.majiang.community.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/8/6
 * <p>
 * 自定义异常实现类
 */
public class CustomizeException extends RuntimeException {

    private String message;
    private Integer code;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }
    public CustomizeException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        //继承自Throwable的方法
         return message;
    }

    public Integer getCode() {
        return code;
    }
}
