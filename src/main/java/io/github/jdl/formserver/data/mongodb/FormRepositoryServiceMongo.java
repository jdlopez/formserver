package io.github.jdl.formserver.data.mongodb;

import io.github.jdl.formserver.config.FormType;
import io.github.jdl.formserver.data.FormRepository;
import io.github.jdl.formserver.domain.FormDefinition;
import io.github.jdl.formserver.domain.FormInstance;
import io.github.jdl.formserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
@Service
public class FormRepositoryServiceMongo implements FormRepository {

    @Autowired
    private FormDefinitionRepoMongo formDefinitionRepoMongo;
    @Autowired
    private FormInstanceRepoMongo formInstanceRepoMongo;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public FormDefinition findById(String  formId) {
        return formDefinitionRepoMongo.findOne(formId);
    }

    @Override
    public void save(FormInstance form) {
        formInstanceRepoMongo.save(form);
    }

    @Override
    public void updateDates(String id, FormInstance form) {
        FormInstance f = formInstanceRepoMongo.findOne(id);
        form.setCreated(f.getCreated());
        form.setUpdated(new Date());
    }

    private FormType getFormType(String type) {
        return applicationContext.getBean(type, FormType.class);
    }

    @Override
    public boolean canSave(FormDefinition formDefinition, User user) {
        return getFormType(formDefinition.getType()).canSave(formDefinition.getId(), user.getId());
    }

    @Override
    public List<FormDefinition> findAll() {
        return formDefinitionRepoMongo.findAll();
    }

    @Override
    public boolean canSend(FormDefinition formDefinition, User user) {
        return getFormType(formDefinition.getType()).canSend(formDefinition.getId(), user.getId());
    }

    @Override
    public void saveDefinition(FormDefinition form) {
        formDefinitionRepoMongo.save(form);
    }

    @Override
    public List<FormInstance> findInstanceByUser(String  formId, String userId) {
        return formInstanceRepoMongo.findByFormIdAndUserId(formId, userId);
    }
}
