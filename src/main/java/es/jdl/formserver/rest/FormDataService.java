package es.jdl.formserver.rest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import es.jdl.formserver.FormsRepository;
import es.jdl.formserver.domain.FormDefinition;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Form instances or data REST services. Today just mongodb storagetype
 * Future: convert in abstract and implementation depends on form storageTypeDef ...
 */
@RestController
public class FormDataService {

    @Autowired
    private MongoClient mongoClient;
    @Autowired
    private FormsRepository formsRepository;

    @GetMapping("/form/{id}/data")
    public FormData listEntries(@PathVariable String id) {
        List<Document> ret = new LinkedList<>();
        FormDefinition f = formsRepository.findById(id).get();
        MongoStorage storage = new MongoStorage(f);
        MongoCollection<Document> coll = mongoClient.getDatabase(storage.database).getCollection(storage.collection);
        for (MongoCursor<Document> it = coll.find().iterator(); it.hasNext(); ) {
            Document d = it.next();
            ret.add(d);
        } // for
        return new FormData(f, ret);
    }

    @PostMapping("/form/{id}/data")
    public Document saveEntry(@PathVariable String id, @RequestBody Document row) {
        FormDefinition f = formsRepository.findById(id).get();
        MongoStorage storage = new MongoStorage(f);
        MongoCollection<Document> coll = mongoClient.getDatabase(storage.database).getCollection(storage.collection);
        coll.insertOne(row);
        return row;
    }

    private class MongoStorage {
        String database;
        String collection;
        public MongoStorage(FormDefinition f) {
            String[] dbColl = f.getStorageKey().split(":");
            database = dbColl[0];
            collection = dbColl[1];
        }
    }

    private class FormData {
        private final FormDefinition def;
        private final List<Document> data;
        public FormData(FormDefinition def, List<Document> data) {
            this.def = def;
            this.data = data;
        }

        public FormDefinition getDef() {
            return def;
        }

        public List<Document> getData() {
            return data;
        }
    }
}
