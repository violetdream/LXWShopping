package com.personal.user.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.personal.tool.exception.ValidateException;
import com.personal.user.constants.SysRetCodeConstants;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

/**
 * 2019/10/24/0024
 * Create by 刘仙伟
 */
@Slf4j
public class JwtTokenUtils {
    private final static String secret="324iu23094u598ndsofhsiufhaf_+0wq-42q421jiosadiusadiasd";

    public static String createJWTToken(String msg){
//        msg = new AESUtil(msg).encrypt();//先对信息进行aes加密(防止被破解）
        String token=null;
        try{
            token= JWT.create().withIssuer("lxw")
                    .withExpiresAt(DateTime.now().plusDays(1).toDate())
                    .withClaim("user",msg)
                    .sign(Algorithm.HMAC256(secret));
        }catch (Exception e){
            throw e ;
        }
        log.info("generate Token : "+token);
        return token;
    }

    public static String validateJWTToken(String token){
        DecodedJWT decodedJWT=null;
        try{
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("lxw")
                    .build();
            decodedJWT=jwtVerifier.verify(token);
            log.info("签名人：" + decodedJWT.getIssuer() + " 加密方式：" + decodedJWT.getAlgorithm() + " 携带信息：" + decodedJWT.getClaim("user").asString());
        }catch (Exception e){
            log.info("jwt解密出现错误，jwt或私钥或签证人不正确");
            throw new ValidateException(SysRetCodeConstants.TOKEN_VALID_FAILED.getCode(), SysRetCodeConstants.TOKEN_VALID_FAILED.getMessage());
        }
        //获得token的头部，载荷和签名，只对比头部和载荷
        String [] headPayload = token.split("\\.");
        //获得jwt解密后头部
        String header = decodedJWT.getHeader();
        //获得jwt解密后载荷
        String payload = decodedJWT.getPayload();
        if(!header.equals(headPayload[0]) && !payload.equals(headPayload[1])){
            throw new ValidateException(SysRetCodeConstants.TOKEN_VALID_FAILED.getCode(),SysRetCodeConstants.TOKEN_VALID_FAILED.getMessage());
        }
        //return new AESUtil(decodedJWT.getClaim("user").asString()).decrypt();
        return decodedJWT.getClaim("user").asString();
    }
}
