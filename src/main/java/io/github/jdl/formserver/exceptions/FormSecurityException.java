package io.github.jdl.formserver.exceptions;

import io.github.jdl.formserver.domain.FormDefinition;
import io.github.jdl.formserver.domain.User;

public class FormSecurityException extends Exception {

    public FormSecurityException(FormDefinition form, User user) {
        super(String.format("Form: '%s' is forbidden for user '%s'", form.getId(), user.getName()));
    }
}
