package com.example.heroku.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.heroku.service.TestService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestServiceImpl implements TestService {

    @Override
    public String test() {
        return null;
    }
}