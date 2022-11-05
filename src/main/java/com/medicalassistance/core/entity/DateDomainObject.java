package com.medicalassistance.core.entity;

import java.util.Date;

/**
 * Base class to store created and updated date and time of the document.
 */
public class DateDomainObject {
    private Date createdAt;

    private Date updatedAt;

    public DateDomainObject() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void update() {
        this.updatedAt = new Date();
    }
}
