package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void insertResume(Resume resume, int ind) {
        storage[size] = resume;
    }

    @Override
    protected void deleteResume(int ind){
        storage[ind] = storage[size - 1];
        storage[size - 1] = null;
    }
}
