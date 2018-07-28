package org.CMPE202.starbucks.controllerlayer.controller.impl;

import org.CMPE202.starbucks.commondto.constant.StarbucksConstants;
import org.CMPE202.starbucks.commondto.model.User;
import org.CMPE202.starbucks.commondto.model.UserSession;
import org.CMPE202.starbucks.controllerlayer.controller.IAuthenticationController;

import org.CMPE202.starbucks.controllerlayer.service.impl.AuthenticationServiceImpl;
import org.CMPE202.starbucks.controllerlayer.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.CMPE202.starbucks.commondto.constant.StarbucksConstants.AUTH;

@RestController
@RequestMapping(value=AUTH)
public class AuthenticationController  implements IAuthenticationController {

    @Autowired
    private StoreServiceImpl storeService;

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @RequestMapping(value= StarbucksConstants.AUTHENTICATE_USER, method=RequestMethod.POST)
    public @ResponseBody
    UserSession authenticateAndCreateSession (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, @RequestBody User user) {

       System.out.println( httpServletRequest.getSession().getId());

        return authenticationService.authenticateAndCreateSession(httpServletRequest, user);

       //Save session in some table with user id and this session id


//        return httpServletRequest.getSession().getId();

          //"Authentication API";
    }



    @RequestMapping(value= StarbucksConstants.LOG_OUT, method=RequestMethod.GET)
    public @ResponseBody String logOutUser (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        System.out.println( httpServletRequest.getSession().getId());

        authenticationService.logOutAndEndSession(httpServletRequest);

        //Save session in some table with user id and this session id


        return httpServletRequest.getSession().getId();

        //"Authentication API";
    }

}
