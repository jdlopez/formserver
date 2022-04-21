package es.jdl.formserver;

import es.jdl.formserver.domain.FormDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FormsRepository extends MongoRepository<FormDefinition, String> {
    long count();
}
