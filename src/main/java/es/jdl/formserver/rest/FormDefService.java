package es.jdl.formserver.rest;

import es.jdl.formserver.FormsRepository;
import es.jdl.formserver.domain.EnumLifeCycle;
import es.jdl.formserver.domain.EnumStorageType;
import es.jdl.formserver.domain.FormDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
public class FormDefService {

    @Autowired
    private FormsRepository formsRepository;

    @GetMapping("/forms")
    public List<FormDefinition> forms() {
        return formsRepository.findAll();
    }

    @GetMapping("/form/{id}")
    public FormDefinition formById(@PathVariable String id) {
        Optional<FormDefinition> ret = formsRepository.findById(id);
        if (ret.isPresent())
            return ret.get();
        else
            return null; // 404 not found
    }

    @PostMapping("/form")
    public FormDefinition save(@RequestBody FormDefinition form) {
        // adudit must come from auth system
        form.setOwner("admin");
        form.setCreationDate(LocalDateTime.now());
        // some defaults:
        if (form.getLifeCycle() == null)
            form.setLifeCycle(EnumLifeCycle.SINGLE);
        if (form.getStorageType() == null)
            form.setStorageType(EnumStorageType.MONGODB);
        return formsRepository.save(form);
    }

}
