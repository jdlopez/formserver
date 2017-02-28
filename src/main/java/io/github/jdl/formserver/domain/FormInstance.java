package io.github.jdl.formserver.domain;

import java.util.Date;

/**
 * Created by ddjlo on 27/02/2017.
 */
public class FormInstance {

    public static final String STATE_SENT = "SENT";
    public static final String STATE_DRAFT = "DRAFT";

    private Long id;
    private Long formId;
    private String userId;
    private String lifeCycle;
    private String state = STATE_DRAFT;
    private Date created;
    private Date updated;
    private Object data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFormId() {
        return formId;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
