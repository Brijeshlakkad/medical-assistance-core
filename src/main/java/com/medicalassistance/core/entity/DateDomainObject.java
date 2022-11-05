package com.medicalassistance.core.entity;

import java.time.ZonedDateTime;

/**
 * Base class to store created and updated date and time of the document.
 */
public class DateDomainObject {
    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;

    public DateDomainObject() {
        this.createdAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void update() {
        this.updatedAt = ZonedDateTime.now();
    }
}
