package io.github.jdl.formserver.domain;

public class StorageType {
    private String name = "mongodb";
    private String entity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
