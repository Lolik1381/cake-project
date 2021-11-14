package com.example.heroku.controller;

import com.example.heroku.model.UserData;
import com.example.heroku.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Controller
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/userData")
public class UserDataController {

    private final UserDataService userDataService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody UserData create(@RequestBody @Valid UserData userData) {
        return userDataService.create(userData);
    }
}