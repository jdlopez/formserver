package io.github.jdl.formserver;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by ddjlo on 28/02/2017.
 */
public class TestMongoDb {

    @Test
    public void testConnection() throws IOException {
        Properties p = new Properties();
        // mongodb://<dbuser>:<dbpassword>@host:port/db
        p.load(new FileInputStream(new File(System.getProperty("user.home"), "formserver.properties")));
        String userName = p.getProperty("mongodb.userName");
        String password = p.getProperty("mongodb.password");
        String host = p.getProperty("mongodb.serverHost");
        String port = p.getProperty("mongodb.serverPort");
        String database = p.getProperty("mongodb.database");
        MongoCredential credential = MongoCredential.createCredential(userName, database, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, Integer.parseInt(port)), Arrays.asList(credential));

        System.out.println(mongoClient.getDB(database).collectionExists("test"));
        mongoClient.close();

    }
}
