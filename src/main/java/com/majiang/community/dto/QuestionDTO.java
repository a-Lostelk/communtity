package com.majiang.community.dto;

import com.majiang.community.model.User;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/24
 *
 * 数据传输层，显示用户帖子需要展示用户名等内容，需要外联user表
 */
@Data
public class QuestionDTO {

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
    private User user;
}
