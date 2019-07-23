package com.majiang.community.controller;

import com.majiang.community.dto.AccessTokenDTO;
import com.majiang.community.dto.GithubUser;
import com.majiang.community.mapper.UserMapper;
import com.majiang.community.model.User;
import com.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: fang
 * @Date: 2019/7/21
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
    private UserMapper userMapper;

    /**
     * 从请求URL中获取参数
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser != null) {
            //将获取到的GitHub用户信息写入到数据库
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtModified());
            userMapper.insert(user);

            //登录成功写入cookies和session
            request.getSession().setAttribute("user", githubUser);
            //页面跳转，redirect跳转的是路径
            return "redirect:/";
        } else {
            return "redirect:/";
        }

    }
}
