package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import helpers.IsHelper;

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

    @JsonIgnore
    public boolean isNotValid() {
        return !isValid();
    }
}
