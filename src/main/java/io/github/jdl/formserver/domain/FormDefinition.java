package io.github.jdl.formserver.domain;

import com.fasterxml.jackson.annotation.JsonView;
import org.bson.Document;
import org.bson.codecs.pojo.annotations.BsonId;

import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
public class FormDefinition {
    @JsonView(Views.Public.class)
    private String id;
    @JsonView(Views.Public.class)
    private String name;
    @JsonView(Views.Public.class)
    private String description;
    @JsonView(Views.Public.class)
    private Document metaData; // jsonschema or whatever. BSON -> vinculamos mucho con Mongodb
    private boolean scopePrivate = true;
    private EnumInstance instance;
    private StorageType storage;
    private String authentication = "none";
    private List<String> authorization; // role List
    private boolean auditing;
    private String lifecyle; // none ... x ahora
    private boolean draft = true;
    private AuditData auditData;

    @BsonId
    public String getId() {
        return id;
    }

    @BsonId
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isScopePrivate() {
        return scopePrivate;
    }

    public void setScopePrivate(boolean scopePrivate) {
        this.scopePrivate = scopePrivate;
    }

    public EnumInstance getInstance() {
        return instance;
    }

    public void setInstance(EnumInstance instance) {
        this.instance = instance;
    }

    public StorageType getStorage() {
        return storage;
    }

    public void setStorage(StorageType storage) {
        this.storage = storage;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public List<String> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(List<String> authorization) {
        this.authorization = authorization;
    }

    public boolean isAuditing() {
        return auditing;
    }

    public void setAuditing(boolean auditing) {
        this.auditing = auditing;
    }

    public String getLifecyle() {
        return lifecyle;
    }

    public void setLifecyle(String lifecyle) {
        this.lifecyle = lifecyle;
    }

    public boolean isDraft() {
        return draft;
    }

    public void setDraft(boolean draft) {
        this.draft = draft;
    }

    public AuditData getAuditData() {
        return auditData;
    }

    public void setAuditData(AuditData auditData) {
        this.auditData = auditData;
    }

    public Document getMetaData() {
        return metaData;
    }

    public void setMetaData(Document metaData) {
        this.metaData = metaData;
    }

    @Override
    public String toString() {
        return "FormDefinition{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", metaData=" + metaData +
                ", scopePrivate=" + scopePrivate +
                ", instance=" + instance +
                ", storage=" + storage +
                ", authentication='" + authentication + '\'' +
                ", authorization=" + authorization +
                ", auditing=" + auditing +
                ", lifecyle='" + lifecyle + '\'' +
                ", draft=" + draft +
                ", auditData=" + auditData +
                '}';
    }
}
