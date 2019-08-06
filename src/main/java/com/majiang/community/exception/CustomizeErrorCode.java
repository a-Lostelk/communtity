package com.majiang.community.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/8/6
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在了，要不换一个问题试试");

    @Override
    public String getMessage() {
        return message;
    }
    private String message;
    CustomizeErrorCode(String message) {
        this.message = message;
    }
}

