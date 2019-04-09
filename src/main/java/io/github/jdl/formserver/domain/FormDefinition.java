package io.github.jdl.formserver.domain;

import java.util.List;

/**
 * Created by ddjlo on 27/02/2017.
 */
public class FormDefinition {
    private String id;
    private String name;
    private String description;
    private boolean scopePrivate = true;
    private EnumInstance instance;
    private StorageType storage;
    private String authentication = "none";
    private List<String> authorization; // role List
    private boolean auditing;
    private String lifecyle; // none ... x ahora
    private boolean draft = true;
    private AuditData auditData;

    public String getId() {
        return id;
    }

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
}
