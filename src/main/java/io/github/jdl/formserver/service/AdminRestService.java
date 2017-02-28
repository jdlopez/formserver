package io.github.jdl.formserver.service;

import io.github.jdl.formserver.config.DbConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ddjlo on 27/02/2017.
 */
@RestController
@RequestMapping("/admin")
public class AdminRestService {

    @Autowired
    private DbConfiguration dbConfiguration;

    @RequestMapping(path = "/config", method = RequestMethod.POST)
    public void saveConfig(@RequestParam("database") String database, @RequestParam("password") String password,
                           @RequestParam("serverHost") String serverHost, @RequestParam("serverPort") int serverPort,
                           @RequestParam("userName") String userName) {
        dbConfiguration.setDatabase(database);
        dbConfiguration.setPassword(password);
        dbConfiguration.setServerHost(serverHost);
        dbConfiguration.setServerPort(serverPort);
        dbConfiguration.setUserName(userName);
        dbConfiguration.store();
    }

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public Map<String, Object> getConfig() {
        Map<String, Object> ret = new HashMap<>();
        ret.put("database", dbConfiguration.getDatabase());
        ret.put("serverHost", dbConfiguration.getServerHost());
        ret.put("serverPort", dbConfiguration.getServerPort());
        ret.put("password", dbConfiguration.getPassword());
        ret.put("userName", dbConfiguration.getUserName());
        return ret;
    }
}
