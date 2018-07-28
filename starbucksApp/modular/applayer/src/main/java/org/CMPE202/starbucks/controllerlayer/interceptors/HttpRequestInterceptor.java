package org.CMPE202.starbucks.controllerlayer.interceptors;

import org.CMPE202.starbucks.controllerlayer.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpRequestInterceptor implements HandlerInterceptor {

    @Autowired
    AuthenticationServiceImpl authenticationService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String error= null;
        System.out.println(httpServletRequest.getSession().getId());
        System.out.println( httpServletRequest.getServletPath());
        System.out.println( httpServletRequest.getHeader("USER_ID"));

        String userId = httpServletRequest.getHeader("USER_ID");
        String sessionId = httpServletRequest.getHeader("SESSION_ID");

        //If servlet path is authenticate/login then return true
        if("/auth/authenticateUser".equals(httpServletRequest.getServletPath())){
            return true;
        }
        else{
            if(sessionId!=null ) {
                error = authenticationService.validateSesion(userId, sessionId);
                if(error == null) {
                    return true;
                }
                else {
                    httpServletResponse.setContentType("application/json");
                    httpServletResponse.getWriter().write(error);
                }
            }
            else{
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter().write("Invalid Session");
            }

        }
        return false;

        //else validate the call using HttpServletRequest session and return true or false
    }



    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
