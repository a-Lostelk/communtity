package com.majiang.community.exception;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/8/6
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不存在了，要不换一个问题试试"),
    TAGGET_PARAM_NOT_FOUND(2002, "未选中任何问题或是回复"),
    NOT_LOG(2003, "当前操作需要登录。请登录后重试"),
    SYS_ERROR(2004, "服务器发生异常错误，请您稍后重试"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006,"查找的评论不存在"),;



    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

