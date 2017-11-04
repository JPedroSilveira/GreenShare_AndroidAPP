package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by jpedr on 04/11/2017.
 */

public interface BasicEntity<Entity> {

    Long getId();

    @JsonIgnore
    boolean isValid();

    void update(Entity e);

}
