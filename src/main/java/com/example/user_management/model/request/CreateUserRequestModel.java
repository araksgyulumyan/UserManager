package com.example.user_management.model.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateUserRequestModel {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
