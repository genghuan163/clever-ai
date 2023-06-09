package com.jjh.cleverai.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jjh.cleverai.model.TUsers;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET = "55c72a45f613af9f";

    /**
     * 过期时间 ms  30分钟
     **/
    public static final long EXPIRATION = 1800L;

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(TUsers user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map)// 添加头部
                //可以将基本信息放到claims中
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("password", user.getPassword())
                .withClaim("phoneNumber", user.getPhoneNumber())
                .withExpiresAt(expireDate)
                .withIssuedAt(new Date())
                .sign(Algorithm.HMAC256(SECRET));
    }


    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("token解码异常");
            return null;
        }
        return jwt.getClaims();
    }

}
