package com.company.controller;

import com.company.model.AccountLogin;
import com.company.model.ApiResponse;
import com.company.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid AccountLogin accountLogin) {
        loginService.login(accountLogin);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode("200");
        apiResponse.setMessage("Operator successful");
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
