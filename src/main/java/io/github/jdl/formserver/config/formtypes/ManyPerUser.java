package io.github.jdl.formserver.config.formtypes;

import io.github.jdl.formserver.config.FormType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Created by ddjlo on 27/02/2017.
 */
@Component
@Qualifier("ManyPerUser")
public class ManyPerUser implements FormType {
    @Override
    public boolean canSave(String formId, String userId) {
        return true;
    }

    @Override
    public boolean canSend(String formId, String userId) {
        return true;
    }
}
