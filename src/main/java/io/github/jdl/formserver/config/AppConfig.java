package io.github.jdl.formserver.config;

import com.google.appengine.api.datastore.EntityNotFoundException;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import io.github.jdl.formserver.data.mongodb.FormDefinitionRepoMongo;
import io.github.jdl.formserver.data.mongodb.FormInstanceRepoMongo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by ddjlo on 27/02/2017.
 */
@Configuration
@EnableWebMvc
@EnableMongoRepositories(basePackageClasses = {FormDefinitionRepoMongo.class, FormInstanceRepoMongo.class})
@ComponentScan(basePackages={"io.github.jdl"})
public class AppConfig extends WebMvcConfigurerAdapter implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = Logger.getLogger(AppConfig.class.getName());

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**.html", "/**.js", "/**.css")
                .addResourceLocations("/");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Inicializando/refrescando contexto: " + event.toString());
        // inicializo configuracion
        //dbConfiguration = event.getApplicationContext().getBean(DbConfiguration.class);
    }

    @Bean
    public DbConfiguration dbConfiguration() throws EntityNotFoundException {
        DbConfiguration ret = new DbConfiguration();
        ret.init();
        return ret;
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException, EntityNotFoundException {
        DbConfiguration cfg = dbConfiguration();
        if (cfg != null) {
            MongoCredential credential = MongoCredential.createCredential(cfg.getUserName(), cfg.getDatabase(), cfg.getPassword().toCharArray());
            MongoClient mongoClient = new MongoClient(new ServerAddress(cfg.getServerHost(), cfg.getServerPort()), Arrays.asList(credential));
            MongoDbFactory ret = new SimpleMongoDbFactory(mongoClient, cfg.getDatabase());
            log.info("Creando conexion a la bbdd : " + ret);
            return ret;
        } else {
            throw new NullPointerException("No se ha creado el servicio DbConfiguration!");
        }
    }

    @Bean
    public MongoTemplate mongoTemplate() throws UnknownHostException, EntityNotFoundException {
        MongoTemplate ret = new MongoTemplate(mongoDbFactory());
        return ret;
    }

}
