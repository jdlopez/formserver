package io.github.jdl.formserver.data.mongodb;

import io.github.jdl.formserver.domain.FormDefinition;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ddjlo on 27/02/2017.
 * No es necesario anotar la clase FormDefinitioin xq:
 * id fits the standard name for a MongoDB id so it doesn’t require any special annotation to tag it for Spring Data MongoDB.
 */
public interface FormDefinitionRepoMongo extends MongoRepository<FormDefinition, Long> {

}
