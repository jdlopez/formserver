package es.jdl.formserver.domain;

import lombok.Data;
import org.bson.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class FormDefinition {
    // id
    @Id
    private String id;
    private String shortName; // part of URL
    private String title; // public title
    private String description; // private desc
    // status / security?
    private boolean active;
    private boolean scopePublic;
    // operative data
    private Document metaData;
    private EnumStorageType storageType;
    private String storageKey; // in mongo->db:collection
    private String listFilter;
    private EnumLifeCycle lifeCycle;
    // audit
    private LocalDateTime creationDate;
    private String owner;

}
