package io.github.jdl.formserver.data.mongodb;

import io.github.jdl.formserver.domain.User;

/**
 * Created by ddjlo on 28/02/2017.
 */
public class BasicUser implements User {
    private final String name;
    private final String id;

    public BasicUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }
}
