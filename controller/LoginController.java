package com.example.LoginPage.controller;

import com.example.LoginPage.domain.Login;
import com.example.LoginPage.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.http.HttpResponse;
import java.util.Objects;

@Controller
public class LoginController {

@Autowired
    private LoginService userService;

@GetMapping("/login")
public ModelAndView login(){
    ModelAndView mav = new ModelAndView("login");
    mav.addObject("user",new Login());
    return mav;
}

@PostMapping("/login")
    public String login(@ModelAttribute("user") Login user){
    Login oauthUser = userService.login(user.getUsername(), user.getPassword());
    System.out.print(oauthUser);
    if (Objects.nonNull(oauthUser)){
         return "redirect:/";
    }
    else {
        return "redirect:/login";
    }
}

@RequestMapping(value = {"/logout"},method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request, HttpResponse response){
    return "redirect:/login";
}

}
