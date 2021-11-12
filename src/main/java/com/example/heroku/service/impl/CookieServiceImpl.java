package com.example.heroku.service.impl;

import com.example.heroku.service.CookieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CookieServiceImpl implements CookieService {

    @Override
    public String write(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("basket", UUID.randomUUID().toString());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);

        return "index";
    }
}