package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.provider.GithubProvider;
import com.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/21
 *
 * 用于github的授权认证
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    /**
     * application.properties文件中的
     */
    @Value("${github.client.Client_id}")
    private String clientId;

    @Value("${github.client.Client_secret}")
    private String clientSecret;

    @Value("${github.client.Redirect_uri}")
    private String redirectUri;

    @Autowired
    private UserService userService;

    /**
     * 从请求URL中获取参数
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null && githubUser.getId() != null) {
            //将获取到的GitHub用户信息写入到数据库
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setAvatarUrl(githubUser.getAvatar_url());
            userService.createOrUpdate(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            //页面跳转，redirect跳转的是路径
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }

    /**
     * 退出登录功能
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}