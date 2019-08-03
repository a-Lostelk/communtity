package com.majiang.community.mapper;

import com.majiang.community.model.User;
import org.apache.ibatis.annotations.*;

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
    @Insert("INSERT INTO user (name,token,accountId,gmtCreate,gmtModified,avatarUrl) VALUES (#{name},#{token},#{accountId},#{gmtCreate},#{gmtModified},#{avatarUrl}) ")
    void insert(User user);

    /**
     *  通过token凭证查找用户
     * @param token 注解将形参中的非Java类参数绑定到SQL语句中
     * @return
     */
    @Select("SELECT * FROM user where token=#{token}")
    User findUserByToken(@Param("token") String token);

    /**
     * 通过id查找用户
     */
    @Select("SELECT * FROM user where id=#{id} ")
    User findUserById(@Param("id") Integer id);

    /**
     * 根据accountId查找用户
     * @return
     */
    @Select("SELECT * FROM user where accountId=#{accountId} ")
    User findUserByAccountId(@Param("accountId") String accountId);

    @Update("UPDATE user SET name=#{name},token=#{token},gmtCreate=#{gmtCreate},gmtModified=#{gmtModified},avatarUrl=#{avatarUrl} where id=#{id}")
    void updateUser(User user);
}
