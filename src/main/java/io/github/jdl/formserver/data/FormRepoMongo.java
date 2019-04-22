package io.github.jdl.formserver.data;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import io.github.jdl.formserver.domain.AppConfig;
import io.github.jdl.formserver.domain.FormDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormRepoMongo implements FormRepository {

    @Autowired
    private MongoClient mongoClient;
    @Autowired
    private AppConfig config;

    protected MongoCollection<FormDefinition> getCollection() {
        return mongoClient.getDatabase(config.getDatabaseName()).getCollection("forms", FormDefinition.class);
    }

    @Override
    public FormDefinition findById(String id) {
        return getCollection().find(Filters.eq("id", id)).first();
    }

    @Override
    public void save(FormDefinition formDefinition) {
        getCollection().insertOne(formDefinition);
    }

    @Override
    public Iterable<FormDefinition> findAll() {
        return getCollection().find();
    }
}
