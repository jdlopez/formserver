package io.github.jdl.formserver.config;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface FormType {

    public boolean canSave(Long formId, String userId);
    public boolean canSend(Long formId, String userId);
}
