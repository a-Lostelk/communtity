package com.majiang.community.service;

import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/8/3
 */
@Service
public class UserService  {

    @Autowired
    private UserMapper userMapper;

    /**
     * 调用授权登录功能时，根据AccountId判断用户是否存在后执行创建或者更新操作
     * @param user
     */
    public void createOrUpdate(User user) {
        User dbuser = userMapper.findUserByAccountId(user.getAccountId());
        if (dbuser == null) {
            //数据库之后不存在用户执行插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate() );
            userMapper.insert(user);
        } else {
            //更新
            dbuser.setGmtCreate(System.currentTimeMillis());
            dbuser.setGmtModified(dbuser.getGmtCreate());
            dbuser.setAvatarUrl(user.getAvatarUrl());
            dbuser.setName(user.getName());
            dbuser.setToken(user.getToken());
            userMapper.updateUser(dbuser);
        }
    }
}
