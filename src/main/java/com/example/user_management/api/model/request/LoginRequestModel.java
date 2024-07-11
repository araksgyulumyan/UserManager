package com.example.user_management.api.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestModel {

    @JsonProperty(value = "username", required = true)
    @NotEmpty
    private String username;

    @JsonProperty(value = "password", required = true)
    @NotEmpty
    private String password;
}
