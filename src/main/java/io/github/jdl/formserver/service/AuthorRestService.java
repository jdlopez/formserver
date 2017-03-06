package io.github.jdl.formserver.service;

import io.github.jdl.formserver.data.FormRepository;
import io.github.jdl.formserver.data.UserRepository;
import io.github.jdl.formserver.domain.FormDefinition;
import io.github.jdl.formserver.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
@RestController
@RequestMapping("/author")
public class AuthorRestService {

    @Autowired
    private FormRepository formRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/form/list", method = RequestMethod.GET)
    public List<FormDefinition> getAll() {
        return formRepository.findAll();
    }

    @RequestMapping(path = "/form", method = RequestMethod.POST)
    public FormDefinition save(@RequestBody FormDefinition form, HttpServletRequest request) {
        User user = userRepository.findByRequest(request);
        form.setOwner(user.getId());
        if (form.getCreated() == null)
            form.setCreated(new Date());
        formRepository.saveDefinition(form);
        return form;
    }

    @RequestMapping(path = "/form/{formId}", method = RequestMethod.GET)
    public FormDefinition getForm(@PathVariable("formId") String formId) {
        return formRepository.findById(formId);
    }
}
