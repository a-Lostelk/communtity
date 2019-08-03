package com.majiang.community.model;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/23
 */
@Data
public class User {

    private Integer id;

    private String name;

    private String accountId;

    private String token;

    private Long gmtCreate;

    private Long gmtModified;

    private String avatarUrl;
}
