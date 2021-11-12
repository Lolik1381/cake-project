package com.example.heroku.service;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {

    String write(HttpServletRequest request, HttpServletResponse response);
}