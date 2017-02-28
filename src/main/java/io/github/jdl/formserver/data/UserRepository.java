package io.github.jdl.formserver.data;

import io.github.jdl.formserver.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface UserRepository {

    User findByRequest(HttpServletRequest request);
}
