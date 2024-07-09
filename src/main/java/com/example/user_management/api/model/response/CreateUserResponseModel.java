package com.example.user_management.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class CreateUserResponseModel {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;
}
