package com.daily.report.excel.edit.service;

import com.daily.report.excel.edit.core.jpa.entity.LoginEntity;
import com.daily.report.excel.edit.core.jpa.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    public LoginEntity findByLoginDt(String loginDt) {
        return loginRepository.findByLoginDt(loginDt);
    }
}
