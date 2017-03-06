package io.github.jdl.formserver.config.formtypes;

import io.github.jdl.formserver.config.FormType;
import io.github.jdl.formserver.data.FormRepository;
import io.github.jdl.formserver.domain.FormInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
@Component
@Qualifier("OnePerUser")
public class OnePerUser implements FormType {
    @Autowired
    private FormRepository formRepository;

    @Override
    public boolean canSave(String formId, String userId) {
        List<FormInstance> forms = formRepository.findInstanceByUser(formId, userId);
        return forms == null || forms.isEmpty();
    }

    @Override
    public boolean canSend(String formId, String userId) {
        List<FormInstance> forms = formRepository.findInstanceByUser(formId, userId);
        if (forms != null && forms.size() == 1)
            return FormInstance.STATE_DRAFT.equals( forms.get(0).getState() );
        else
            return false;
    }
}
