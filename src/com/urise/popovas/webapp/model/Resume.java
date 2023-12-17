package com.urise.popovas.webapp.model;

import java.util.Objects;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    // Unique identifier
    private String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume r) {
        return uuid.compareTo(r.getUuid());
    }
}
