package com.jjh.cleverai.jwt;

import cn.hutool.core.text.CharSequenceUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jjh.cleverai.annotation.PassToken;
import com.jjh.cleverai.cache.TokenCache;
import com.jjh.cleverai.common.ORuntimeException;
import com.jjh.cleverai.common.enums.ResponseEnum;
import com.jjh.cleverai.model.TUsers;
import com.jjh.cleverai.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {


    private final String TOKEN = "token";
    private final String ERROR = "error";

    @Autowired
    private TokenCache tokenCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN);
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.filter()) {
                return true;
            }
        }
        if (method.getName().equals(ERROR)) {
            throw new ORuntimeException(ResponseEnum.SYSTEM_FAIL);
        }
        if (CharSequenceUtil.isBlank(token)) {
            throw new ORuntimeException(ResponseEnum.TOKEN_EX);
        }
        try {
//            Map<String, Claim> stringClaimMap = JwtUtil.verifyToken(token);
//            TUsers tUsers = new TUsers();
//            if (stringClaimMap != null) {
//                BeanUtils.copyProperties(stringClaimMap,tUsers);
//            }
            JwtUtil.verifyToken(token);
        } catch (JWTDecodeException j) {
            throw new ORuntimeException(ResponseEnum.TOKEN_EX);
        }

        TUsers user = tokenCache.genToken(token);
        if (user == null) {
            throw new ORuntimeException(ResponseEnum.USER_EX);
        }
        return true;
    }
}
