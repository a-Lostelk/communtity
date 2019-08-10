package com.majiang.community.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/8/9
 */
@Data
public class CommentDTO {

    private Long parentId;
    private String content;
    private Integer type;
}
