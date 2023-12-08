package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null );
        size = 0;
    }

    public void update(Resume resume) {
        int index = getUuidIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            return;
        }
        System.out.println("ERROR: resume" + resume.getUuid() + " does not exist");
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("ERROR: impossible to save resume " + resume.getUuid() + ". Array overflow");
            return;
        }

        if (getUuidIndex(resume.getUuid()) != -1) {
            System.out.println("ERROR: resume" + resume.getUuid() + " already exists");
            return;
        }
        storage[size] = resume;
        size++;
    }

    public Resume get(String uuid) {
        int index = getUuidIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.println("ERROR: resume" + uuid + " does not exist");
        return null;
    }

    public void delete(String uuid) {
        int index = getUuidIndex(uuid);
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            return;
        }
        System.out.println("ERROR: resume" + uuid + " does not exist");
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getUuidIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid() == uuid) {
                return i;
            }
        }
        return -1;
    }
}
