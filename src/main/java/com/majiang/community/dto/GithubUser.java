package com.majiang.community.dto;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/22
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;

    /**
     * 用户头像
     */
    private String avatar_url;

}
