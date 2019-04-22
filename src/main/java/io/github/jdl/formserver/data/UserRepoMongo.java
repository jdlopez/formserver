package io.github.jdl.formserver.data;

import io.github.jdl.formserver.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserRepoMongo implements UserRepository {

    @Override
    public User findByName(String name) {
        return null;
    }
}
