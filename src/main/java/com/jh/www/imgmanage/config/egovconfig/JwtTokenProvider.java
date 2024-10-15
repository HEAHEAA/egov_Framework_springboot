package com.jh.www.imgmanage.config.egovconfig;

import com.jh.www.imgmanage.domain.Token;
import com.jh.www.imgmanage.user.service.UserService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "secretcode";

    @Autowired
    private final UserService u_service;
    private final long tokenValidTime = 24*60* 60 * 1000L;
    private final long refreshTokenValidTime = 7*24*60* 60 *1000L;
    private final UserDetailsService userDetailsService;


    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public Token createAllToken(String userPk){
        Token token = new Token();
        token.setAccess_token(createToken(userPk));
        token.setRefresh_token(createRefreshToken(userPk));
        token.setUser_id(userPk);
        token.setRoles(getRoles(userPk));
        token.setExpires_in((int)tokenValidTime/1000);
        token.setToken_type("Bearer");
        return token;
    }
    // JWT 토큰 생성
    public String createToken(String userPk) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles",getRoles(userPk));
        Date now = new Date();
        String accessToken =
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime() + tokenValidTime))
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();
        return accessToken;
    }

    // create refresh Token
    public String createRefreshToken(String userPk){
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles",getRoles(userPk));
        Date now = new Date();
        String  refreshToken =
                Jwts.builder()
                        .setClaims(claims)
                        .setIssuedAt(now)
                        .setExpiration(new Date(now.getTime()+ refreshTokenValidTime))
                        .signWith(SignatureAlgorithm.HS256,secretKey)
                        .compact();
        Token token = new Token();
        token.setUser_id(userPk);
        token.setRefresh_token(refreshToken);
        u_service.updateRefreshToken(token);

        return refreshToken;
    }
    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public int getRoles(String userPk){
        int roles = u_service.getUser(userPk).getSys_op_user_class_id();
        return roles;
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
    public int getUserRole(String token) {
        return (int) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("roles");
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(Pattern.matches("^Bearer .*",token)){
            token = token.replaceAll("^Bearer( )*","");
        }
        return token;
    }
    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("refresh_token");
    }
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if(!claims.getBody().getExpiration().before(new Date())){
                String s_token = u_service.validRefreshToken(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
                return token.equals(s_token);
            }else{
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public Token refreshAccessToken(String refreshToken){
        Token token = new Token();

        try{
            if(validateRefreshToken(refreshToken)){
                token.setAccess_token(createToken(getUserPk(refreshToken)));
                token.setToken_type("Bearer");
                token.setExpires_in((int)tokenValidTime/1000);
                token.setUser_id(getUserPk(refreshToken));
            }
        }
        catch(ExpiredJwtException e){
            System.out.println("");
        }

        return token;
    }

}
