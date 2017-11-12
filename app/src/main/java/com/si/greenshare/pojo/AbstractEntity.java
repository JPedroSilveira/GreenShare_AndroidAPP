package com.si.greenshare.pojo;

import java.util.ArrayList;
import java.util.List;

import com.si.greenshare.helpers.IsHelper;

/**
 * Created by joao.silva
 */
public abstract class AbstractEntity<Entity> extends IsHelper implements BasicEntity<Entity> {

    protected List<String> validationErrors;

    protected AbstractEntity() {
        this.validationErrors = new ArrayList<String>();
    }

    public List<String> getValidationErrors() {
        return this.validationErrors;
    }

    public boolean isNotValid() {
        return !isValid();
    }
}
