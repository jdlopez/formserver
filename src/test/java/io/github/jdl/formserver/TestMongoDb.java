package io.github.jdl.formserver;

import com.mongodb.client.MongoClient;
import io.github.jdl.formserver.config.AppRunnerConfig;
import io.github.jdl.formserver.data.FormRepository;
import io.github.jdl.formserver.domain.EnumInstance;
import io.github.jdl.formserver.domain.FormDefinition;
import io.github.jdl.formserver.domain.StorageType;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by ddjlo on 28/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppRunnerConfig.class)
public class TestMongoDb {

    @Autowired
    private FormRepository formRepository;

    @Test
    public void testConnection() throws IOException {
        MongoClient mongoClient = new AppRunnerConfig().databaseClient();
        // listDatabaseNames: only for admin connections!!
        //mongoClient.listDatabaseNames().forEach((Consumer<? super String>) s -> System.out.println(s));
        System.out.println(mongoClient.getDatabase("mytest"));
        mongoClient.close();

    }

    @Test
    public void testInsertSomeData() throws IOException, URISyntaxException {
        FormDefinition f1 = new FormDefinition();
        f1.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        f1.setAuditing(false);
        f1.setAuthentication("none");
        f1.setInstance(EnumInstance.many);
        f1.setLifecyle("none");
        f1.setName("testOne");
        f1.setDescription("This is a test form");
        //ObjectMapper om = new ObjectMapper();
        //om.readTree(getClass().getResource("/testOne.json"))
        // new String(Files.readAllBytes(Paths.get(getClass().getResource("/testOne.json").toURI())))
        f1.setMetaData(Document.parse(new String(Files.readAllBytes(Paths.get(getClass().getResource("/testOne.json").toURI())))));
        f1.setStorage(new StorageType("testOne"));
        formRepository.save(f1);
        // new id?
        System.out.println("id? " + f1.getId());
    }

    @Test
    public void testListAll() {
        formRepository.findAll().forEach(f -> System.out.println(f));
    }
}
