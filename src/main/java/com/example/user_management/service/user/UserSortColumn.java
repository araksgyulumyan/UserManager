package com.example.user_management.service.user;

import lombok.Getter;

@Getter
public enum UserSortColumn {

    ID("id");

    private final String value;

    UserSortColumn(String id) {
        this.value = id;
    }

}
