package com.example.user_management.api.converter;

import com.example.user_management.api.model.request.LoginRequestModel;
import com.example.user_management.dto.LoginUserDto;
import com.example.user_management.dto.RegisterUserDto;
import com.example.user_management.api.model.request.RegisterUserRequestModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModelConverter {

    public RegisterUserDto toRegisterUserDto(final RegisterUserRequestModel registerUserRequestModel) {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername(registerUserRequestModel.getUsername());
        registerUserDto.setPassword(registerUserRequestModel.getPassword());
        return registerUserDto;
    }

    public LoginUserDto toLoginUserDto(final LoginRequestModel loginRequestModel) {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setUsername(loginRequestModel.getUsername());
        loginUserDto.setPassword(loginRequestModel.getPassword());
        return loginUserDto;
    }
}
