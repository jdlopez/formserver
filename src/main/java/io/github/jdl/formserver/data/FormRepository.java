package io.github.jdl.formserver.data;

import io.github.jdl.formserver.domain.FormDefinition;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface FormRepository {


    FormDefinition findById(String id);

    void save(FormDefinition formDefinition);

    Iterable<FormDefinition> findAll();
}
