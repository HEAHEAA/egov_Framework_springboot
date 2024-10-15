package com.jh.www.imgmanage.config.egovconfig;

import com.jh.www.imgmanage.domain.LogData;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        try{
            String access_token = jwtTokenProvider.resolveToken(request);
            if(jwtTokenProvider.validateToken(access_token)){
                LogData logData = new LogData();
                logData.setUrl(request.getRequestURI());
                logData.setUrl_type("api");
                logData.setUser_id(jwtTokenProvider.getUserPk(access_token));
                return true;
            }else{
                jwtTokenProvider.getUserPk(access_token);
                return false;
            }
        }catch(ExpiredJwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"401- token was expired");
            return false;
        }catch(MalformedJwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"JWT strings must contain exactly 2 period characters. Found: 0");
            return false;
        }catch(NullPointerException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"jwt token unfounded check your source");
            return false;
        }catch(JwtException e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"jwt token is unrecognized");
            return false;
        }
    }
}
