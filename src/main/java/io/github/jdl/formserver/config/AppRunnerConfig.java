package io.github.jdl.formserver.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import io.github.jdl.formserver.domain.AppConfig;
import io.github.jdl.formserver.domain.FormDefinition;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication (scanBasePackages = {"io.github.jdl.formserver.data", "io.github.jdl.formserver.service"})
public class AppRunnerConfig {

    public static void main(String[] args) {
        SpringApplication.run(AppRunnerConfig.class, args);
    }

    @Bean
    public MongoClient databaseClient() {
        // getting connection string from properties: heroku way!
        //http://mongodb.github.io/mongo-java-driver/3.10/driver/getting-started/quick-start-pojo/
        //http://mongodb.github.io/mongo-java-driver/3.10/driver/getting-started/quick-start/
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().register(FormDefinition.class.getPackage().getName())
                .conventions(Conventions.DEFAULT_CONVENTIONS)
                .build();
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));

        String mongouri = System.getenv("MONGODB");
        config().setDatabaseName(mongouri.substring(mongouri.lastIndexOf('/') + 1));

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(pojoCodecRegistry)
                .applyConnectionString(new ConnectionString(mongouri))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        return mongoClient;
    }

    @Bean
    public AppConfig config() {
        // initial config here:
        return new AppConfig();
    }

}
