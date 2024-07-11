package com.example.user_management.api.model.common;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class ResponseModel<T> {

    protected T body;

    protected  List<ErrorCustomModel> errors = new ArrayList<>();

    public boolean hasErrors() {
        return !CollectionUtils.isEmpty(errors);
    }

}


