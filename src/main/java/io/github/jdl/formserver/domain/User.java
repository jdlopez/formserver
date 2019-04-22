package io.github.jdl.formserver.domain;

import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface User {

    String getName();
    String getId();
    List<String> getAuthorization(); // roles
}
