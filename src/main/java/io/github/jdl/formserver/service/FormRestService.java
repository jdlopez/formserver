package io.github.jdl.formserver.service;

import io.github.jdl.formserver.data.FormRepository;
import io.github.jdl.formserver.data.UserRepository;
import io.github.jdl.formserver.domain.FormDefinition;
import io.github.jdl.formserver.domain.User;
import io.github.jdl.formserver.exceptions.FormSecurityException;
import io.github.jdl.formserver.exceptions.FormServerException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by ddjlo on 27/02/2017.
 */
@RestController
@RequestMapping("/api")
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

    /*
- [ ] /api/<id> GET get schema
- [ ] /api/<id> POST save form instance
- [ ] /api/<id>/data GET get a list of all form instances of given type
- [ ] /api/<id>/headers GET form def headers (suitable for datatables)
- [ ] /api/forms GET list available formdefinitions {name, description, id, public? }
     */

    public FormDefinition getForm(@PathVariable("id") String id, Principal principal) throws FormSecurityException {

        FormDefinition form = formRepository.findById(id);
        User user = userRepository.findByName(principal.getName());
        if (checkSecurity(form, user))
            return form;
        else
            throw new FormSecurityException(form, user);
    }

    private boolean checkSecurity(FormDefinition form, User user) {
        return !form.isScopePrivate() ||
                (user != null && form.getAuthorization() != null
                        && form.getAuthorization().stream().anyMatch(role -> user.getAuthorization().contains(role)) );
    }
}
