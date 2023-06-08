package edu.imunet.equipmentmaterials.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import edu.imunet.equipmentmaterials.config.auth.JwtToken;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    //设置过期时间
    private static final long EXPIRE_DATE=30*60*100000;
    //token秘钥
    private static final String TOKEN_SECRET = "ZCfasfhuaUUHufguGuwu2020BQWE";

    public static String token (String userid){
        try {
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //秘钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            //设置头部信息
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带userName，password信息，生成签名
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId",userid).withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
    }
    public static String verify(String token){
        /**
         * @desc   验证token，通过返回true
         * @params [token]需要校验的串
         **/
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            DecodedJWT temp = JWT.decode(token);
            Claim claim = jwt.getClaim("userId");
            return claim.asString();

        }catch (Exception e){
            e.printStackTrace();
            return "0";
        }
    }

    public static void main(String[] args) {
        System.out.println(token("1"));

    }
}
