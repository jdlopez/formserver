package io.github.jdl.formserver.data;

import io.github.jdl.formserver.domain.User;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface UserRepository {

    User findByName(String name);
}
