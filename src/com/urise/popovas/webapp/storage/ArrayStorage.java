package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    final static int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[ArrayStorage.STORAGE_LIMIT];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("ERROR: resume" + resume.getUuid() + " does not exist");
            return;
        }
        storage[index] = resume;
    }

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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume" + uuid + " does not exist");
            return null;
        }
        return storage[index];
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
