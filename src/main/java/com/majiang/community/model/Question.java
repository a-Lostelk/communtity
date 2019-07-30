package com.majiang.community.model;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/23
 */
@Data
public class Question {
    private Integer id ;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private String tags ;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}
