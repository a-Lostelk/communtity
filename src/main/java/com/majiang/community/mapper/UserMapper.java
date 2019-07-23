package com.majiang.community.mapper;

import com.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/23
 */
@Mapper
public interface UserMapper {

    /**
     * 新增用户
     * @param user
     */
    @Insert("INSERT INTO user (name,token,account_id,gmt_create,gmt_modified) VALUES (#{name},#{token},#{accountId},#{gmtCreate},#{gmtModified}) ")
    void insert(User user);

}
