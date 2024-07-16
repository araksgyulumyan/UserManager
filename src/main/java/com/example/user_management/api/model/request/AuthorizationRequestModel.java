package com.example.user_management.api.model.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorizationRequestModel {

    @JsonProperty(value = "username", required = true)
    @NotEmpty
    @Size(min = 1, max = 15)
    private String username;

    @JsonProperty(value = "password", required = true)
    @NotEmpty
    @Size(min = 1, max = 25)
    private String password;
}
