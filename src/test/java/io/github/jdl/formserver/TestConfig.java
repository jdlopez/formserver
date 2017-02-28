package io.github.jdl.formserver;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import io.github.jdl.formserver.config.DbConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ddjlo on 28/02/2017.
 */
public class TestConfig {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testCreateConfig() throws IOException {
        Properties p = new Properties();
        p.load(new FileInputStream(new File(System.getProperty("user.home"), "formserver.properties")));
        DbConfiguration cfg = new DbConfiguration();
        cfg.setDatabase(p.getProperty("mongodb.database"));
        cfg.setUserName(p.getProperty("mongodb.userName"));
        cfg.setServerHost(p.getProperty("mongodb.serverHost"));
        cfg.setServerPort(Integer.parseInt(p.getProperty("mongodb.serverPort")));
        cfg.setPassword(p.getProperty("mongodb.password"));
        cfg.store();

    }
}
