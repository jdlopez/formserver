package io.github.jdl.formserver.config;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by ddjlo on 28/02/2017.
 */
@Component
public class DbConfiguration {
    private String userName;
    private char[] password;
    private String database;
    private String serverHost;
    private int serverPort;

    private static final Logger log = Logger.getLogger(DbConfiguration.class.getName());

    @PostConstruct
    protected void init() throws EntityNotFoundException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key configKey = KeyFactory.createKey("AppConfig", "formServer");
        try {
            Entity configEnt = datastore.get(configKey);
            userName = safeString(configEnt.getProperty("userName"));
            password = safeString(configEnt.getProperty("password"), "").toCharArray();
            database = safeString(configEnt.getProperty("database"));
            serverHost = safeString(configEnt.getProperty("serverHost"));
            serverPort = Integer.parseInt(safeString(configEnt.getProperty("serverPort"), "0"));
        } catch (EntityNotFoundException e) {
            log.info("No se ha encontrado la configuracion inicial en datastore: " + e.getMessage());
            // read from systemproperties
            Properties p = System.getProperties();
            setDatabase(p.getProperty("mongodb.database"));
            setUserName(p.getProperty("mongodb.userName"));
            setServerHost(p.getProperty("mongodb.serverHost"));
            setServerPort(Integer.parseInt(p.getProperty("mongodb.serverPort")));
            setPassword(p.getProperty("mongodb.password").toCharArray());
            store();
        }
    }

    private String safeString(Object objStr) {
        return safeString(objStr, null);
    }

    private String safeString(Object objStr, String defaultValue) {
        if (objStr != null)
            return objStr.toString();
        else
            return defaultValue;
    }

    public void store() {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Entity configEnt = new Entity("AppConfig", "formServer");
        configEnt.setProperty("userName", userName);
        configEnt.setProperty("password", password.toString());
        configEnt.setProperty("database", database);
        configEnt.setProperty("serverHost", serverHost);
        configEnt.setProperty("serverPort", serverPort);
        datastore.put(configEnt);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
