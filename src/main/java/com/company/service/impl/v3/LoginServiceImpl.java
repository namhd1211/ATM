package com.company.service.impl.v3;

import com.company.model.AccountLogin;
import com.company.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public AccountLogin login(AccountLogin accountLogin) {

        return null;
        //after login successful create token and add it into response to the client
    }
}
