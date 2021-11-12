package com.example.heroku.controller;

import com.example.heroku.service.CookieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/cookie")
public class CookieController {

    private final CookieService cookieService;

    @RequestMapping("/write")
    public String write(HttpServletRequest request, HttpServletResponse response) {
        return cookieService.write(request, response);
    }
}