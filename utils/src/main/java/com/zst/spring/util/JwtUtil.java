package com.zst.spring.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/4 18:33
 * @description ：jwt
 */
public class JwtUtil {
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("secret");

    public static String token() {
        return JWT.create()
                .withIssuer("auth0")
                .sign(ALGORITHM);
    }

    public static boolean verify(String token) {
        JWTVerifier verifier = JWT.require(ALGORITHM)
                .withIssuer("auth0")
                .build();
        try {
            DecodedJWT verify = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return false;
        }
    }
}
