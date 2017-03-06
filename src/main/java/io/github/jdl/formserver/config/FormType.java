package io.github.jdl.formserver.config;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface FormType {

    public boolean canSave(String formId, String userId);
    public boolean canSend(String formId, String userId);
}
