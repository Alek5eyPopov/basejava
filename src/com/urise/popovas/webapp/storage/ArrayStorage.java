package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("ERROR: impossible to save resume " + resume.getUuid() + ". Array overflow");
        } else if (getIndex(resume.getUuid()) != -1) {
            System.out.println("ERROR: resume" + resume.getUuid() + " already exists");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume" + uuid + " does not exist");
            return;
        }
        storage[index] = storage[size - 1];
        storage[size - 1] = null;
        size--;
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
