package com.wms.admin.auth;

import com.wms.admin.commom.ResultCode;
import com.wms.admin.exception.BusinessException;
import com.wms.admin.util.Base64Util;
import com.wms.admin.vo.UserRoleVO;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class JwtTokenUtil {
    public static final String AUTH_HEADER_KEY = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";



    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            log.error("Token过期", eje);
            throw new BusinessException(ResultCode.PERMISSION_TOKEN_EXPIRED);
        } catch (Exception e) {
            log.error("token解析异常", e);
            throw new BusinessException(ResultCode.PERMISSION_TOKEN_INVALID);
        }
    }
    public static String createJWT0(String userId, String username,
                                   List<UserRoleVO> roles,
                                   Audience audience) {
        try {
            //添加构成JWT的参数
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());

            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("roles", roles)
                    .claim("userId", userId)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getClientId())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getName())          // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);

            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis * 1000;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }
            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new BusinessException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }
    }
    public static String createJWT(String userId, String username,
                                   List<UserRoleVO> roles, List<String> resources,
                                   Audience audience) {
        try {
            //添加构成JWT的参数
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());

            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                    // 可以将基本不重要的对象信息放到claims
                    .claim("roles", roles)
                    .claim("userId", userId)
                    .claim("resource", resources)
                    .setSubject(username)           // 代表这个JWT的主体，即它的所有人
                    .setIssuer(audience.getClientId())              // 代表这个JWT的签发主体；
                    .setIssuedAt(new Date())        // 是一个时间戳，代表这个JWT的签发时间；
                    .setAudience(audience.getName())          // 代表这个JWT的接收对象；
                    .signWith(signatureAlgorithm, signingKey);

            //添加Token过期时间
            int TTLMillis = audience.getExpiresSecond();
            if (TTLMillis >= 0) {
                long expMillis = nowMillis + TTLMillis * 1000;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp)  // 是一个时间戳，代表这个JWT的过期时间；
                        .setNotBefore(now); // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
            }
            //生成JWT
            return builder.compact();
        } catch (Exception e) {
            log.error("签名失败", e);
            throw new BusinessException(ResultCode.PERMISSION_SIGNATURE_ERROR);
        }
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUsername(String token, String base64Security) {
        return parseJWT(token, base64Security).getSubject();
    }

    public static UserInfo parseUserInfo(Claims claims) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId((String) claims.get("userId"));
        userInfo.setUsername(claims.getSubject());
        List<PermissionResource> permissions = (List) claims.get("permissions");
        if (permissions != null) {
            userInfo.setPermissions(permissions);
            final List<String> resources = permissions.stream().map(PermissionResource::getMenuCode).collect(Collectors.toList());
            userInfo.setResources(resources);
        }
        List<Map<String, String>> rolesMapping = (List) claims.get("roles");
        List<UserRoleVO> roles = new ArrayList<>();
        rolesMapping.forEach(mapping->{
            UserRoleVO role=new UserRoleVO();
            role.setRoleId(mapping.get("roleId"));
            role.setRoleCode(mapping.get("roleCode"));
            role.setRoleName(mapping.get("roleName"));
            roles.add(role);
        });
        if(roles!=null){
            userInfo.setRoles(roles);
        }
        return userInfo;
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static String getUserId(String token, String base64Security) {
        String userId = parseJWT(token, base64Security).get("userId", String.class);
        return Base64Util.decode(userId);
    }

    public static List getResources(String token, String base64Security) {
        List resources = parseJWT(token, base64Security).get("resources", List.class);
        return resources;
    }

    public static UserInfo getUserInfo(String token, String base64Security) {
        Claims claims = parseJWT(token, base64Security);
        return parseUserInfo(claims);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @param base64Security
     * @return
     */
    public static boolean isExpiration(String token, String base64Security) {
        return parseJWT(token, base64Security).getExpiration().before(new Date());
    }
}
