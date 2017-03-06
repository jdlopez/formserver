package io.github.jdl.formserver.data;

import io.github.jdl.formserver.domain.FormDefinition;
import io.github.jdl.formserver.domain.FormInstance;
import io.github.jdl.formserver.domain.User;

import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface FormRepository {
    
    FormDefinition findById(String  formId);

    void save(FormInstance form);

    /**
     * Actualiza las fechas (creacion y actualizacion de un formulario x su id
     * @param id
     * @param form
     */
    void updateDates(String id, FormInstance form);

    boolean canSave(FormDefinition formDefinition, User user);

    List<FormDefinition> findAll();

    boolean canSend(FormDefinition fDef, User user);

    void saveDefinition(FormDefinition form);

    List<FormInstance> findInstanceByUser(String formId, String userId);
}
