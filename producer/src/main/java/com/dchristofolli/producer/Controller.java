package com.dchristofolli.producer;

import com.dchristofolli.producer.user.UserModel;
import com.dchristofolli.producer.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class Controller {
    private final UserService service;

    public Controller(UserService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody UserModel model) {
        service.sendUser(model);
    }
}