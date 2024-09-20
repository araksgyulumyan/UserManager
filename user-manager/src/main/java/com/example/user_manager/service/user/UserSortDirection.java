package com.example.user_manager.service.user;

public enum UserSortDirection {

    DESC("desc"),
    ASC("asc");

    private final String value;

    UserSortDirection(String direction) {
        this.value = direction;
    }

    public static UserSortDirection from(String value) {
        for (UserSortDirection direction : UserSortDirection.values()) {
            if (direction.value.equals(value)) {
                return direction;
            }
        }
        throw new IllegalArgumentException();
    }
}
