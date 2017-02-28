package io.github.jdl.formserver.service;

import io.github.jdl.formserver.data.FormRepository;
import io.github.jdl.formserver.data.UserRepository;
import io.github.jdl.formserver.domain.FormDefinition;
import io.github.jdl.formserver.domain.FormInstance;
import io.github.jdl.formserver.domain.User;
import io.github.jdl.formserver.exceptions.FormServerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
@RestController
@RequestMapping("/")
public class FormRestService {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MessageSource messages;

    @ExceptionHandler(FormServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleApplicationErrors(FormServerException e, HttpServletRequest request) {
        return messages.getMessage(e.getMessage(), e.getArgs(), e.getMessage(), request.getLocale());
    }

    @ExceptionHandler(BeansException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleConfigBeansErrors(BeansException e, HttpServletRequest request) {
        return messages.getMessage("error.BeansException", new Object[]{ e.getMessage()}, request.getLocale());
    }


    /**
     *
     * @param formId
     * @param request
     * @return
     */
    @RequestMapping(path = "{formId}", method = RequestMethod.GET)
    public FormDefinition newForm(@PathVariable ("formId") Long formId, HttpServletRequest request) {
        // comprobar permisos? por tipo?
        return formRepository.findById(formId);
    }

    @RequestMapping(path = "{formId}", method = RequestMethod.POST)
    public FormInstance saveForm(@RequestBody FormInstance form, HttpServletRequest request) throws FormServerException {
        User user = userRepository.findByRequest(request);
        FormDefinition fDef = formRepository.findById(form.getFormId());
        if (formRepository.canSave(fDef, user)) {
            form.setUserId(user.getId());
            if (form.getId() == null) // new form instance!
                form.setCreated(new Date());
            else {
                formRepository.updateDates(form.getId(), form);
            }

            formRepository.save(form);
        } else {
            throw new FormServerException("No se puede guardar");
        }
        return form;
    }

    @RequestMapping(path = "{formId}/send", method = RequestMethod.POST)
    public FormInstance sendForm(@RequestBody FormInstance form, HttpServletRequest request) throws FormServerException {
        User user = userRepository.findByRequest(request);
        FormDefinition fDef = formRepository.findById(form.getFormId());
        if (formRepository.canSend(fDef, user)) {
            form.setState(FormInstance.STATE_SENT);
        } else {
            throw new FormServerException("No se puede enviar");
        }
        return saveForm(form, request);
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<FormDefinition> getAuthorizedFormDefinitions(HttpServletRequest request) {
        User user = userRepository.findByRequest(request);
        List<FormDefinition> forms = formRepository.findAll();
        for (FormDefinition f: forms) {
            if (!formRepository.canSave(f, user))
                forms.remove(f);
        }
        return forms;
    }

}
