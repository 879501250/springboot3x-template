package com.mijiu.commom.interceptor;


import com.mijiu.commom.enumerate.ResultEnum;
import com.mijiu.commom.exception.TokenOverdueException;
import com.mijiu.commom.util.UserHolder;
import com.mijiu.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import java.util.Objects;


/**
 * @author mijiupro
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("进入登录拦截器");
        User user = UserHolder.getInfoByToken();

        if (Objects.isNull(user)) {
            log.info("过期异常");
            throw new TokenOverdueException(ResultEnum.PERMISSION_EXPIRE);
        }

        log.info("放行");
        return true;
    }
}
