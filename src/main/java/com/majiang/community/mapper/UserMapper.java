package com.majiang.community.mapper;

import com.majiang.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    /**
     * @Param 注解将形参中的非Java类参数绑定到SQL语句中
     */
    @Select("SELECT * FROM user where token=#{token}")
    User findUserByToken(@Param("token") String token);
}
