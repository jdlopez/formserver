package io.github.jdl.formserver.data.mongodb;

import io.github.jdl.formserver.domain.FormInstance;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
public interface FormInstanceRepoMongo extends MongoRepository<FormInstance, String> {
    List<FormInstance> findByFormIdAndUserId(String formId, String userId);
}
