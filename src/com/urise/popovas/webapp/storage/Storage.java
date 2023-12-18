package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

public interface Storage {
    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    Resume[] getAll();
}
