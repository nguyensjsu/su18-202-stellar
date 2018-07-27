package org.CMPE202.starbucks.controller.impl;
import org.CMPE202.starbucks.constant.StarbucksConstants;
import org.CMPE202.starbucks.controller.IAuthenticationController;
import org.CMPE202.starbucks.service.impl.StoreServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static org.CMPE202.starbucks.constant.StarbucksConstants.*;

@RestController
@RequestMapping(value=AUTH)
public class AuthenticationController  implements IAuthenticationController{

    @Autowired
    private AuthenticationServiceImpl authService;

    @RequestMapping(value= StarbucksConstants.AUTHENTICATE_USER, method=RequestMethod.POST)
    public @ResponseBody String authenticateUser (@RequestBody User user) {

    	return authservice.authenticateUser(user);

    }
}
