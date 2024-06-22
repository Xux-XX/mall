package com.xux.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.commonWeb.context.UserContext;
import com.xux.core.util.JWTUtil;
import com.xux.user.mapper.UserMapper;
import com.xux.user.pojo.entity.User;
import com.xux.user.pojo.enums.LoginStatus;
import com.xux.user.service.UserService;
import com.xux.redis.util.LoginRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/14 21:22
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    private final JWTUtil jwtUtil;
    private final LoginRedisUtil redisUtil;


    @Override
    public LoginStatus login(String username, String password) {
        User user = this.lambdaQuery()
                .eq(User::getUsername, username)
                .eq(User::getPassword, password)
                .one();
        // 没查到说明账号或密码错误
        if (user == null) return LoginStatus.FAIL;
        //返回jwt并存入redis
        LoginStatus success = LoginStatus.SUCCESS;
        String jwt = jwtUtil.createJWT(user.getUserId(), user.getRoleStatus());
        success.setData(jwt);
        redisUtil.remove(user.getUserId().toString());
        redisUtil.set(user.getUserId().toString(), jwt);
        return success;
    }

    @Override
    public void logout() {
        // 将redis中jwt删除
        Integer userId = UserContext.get().getUserId();
        redisUtil.remove(userId.toString());
    }
}
