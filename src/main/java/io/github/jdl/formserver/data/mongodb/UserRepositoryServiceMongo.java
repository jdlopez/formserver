package io.github.jdl.formserver.data.mongodb;

import io.github.jdl.formserver.data.UserRepository;
import io.github.jdl.formserver.domain.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ddjlo on 27/02/2017.
 */
@Service
public class UserRepositoryServiceMongo implements UserRepository {

    @Override
    public User findByRequest(HttpServletRequest request) {
        return new BasicUser(request.getUserPrincipal().getName(), request.getUserPrincipal().getName());
    }
}
