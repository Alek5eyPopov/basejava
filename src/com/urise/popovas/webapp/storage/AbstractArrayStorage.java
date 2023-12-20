package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[ArrayStorage.STORAGE_LIMIT];
    protected int size;

    protected abstract int getIndex(String uuid);

    protected abstract void insertResume(Resume resume, int ind);

    protected abstract void deleteResume(int ind);

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

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume" + uuid + " does not exist");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("ERROR: impossible to save resume " + resume.getUuid() + ". Array overflow");
        } else if (index >= 0) {
            System.out.println("ERROR: resume" + resume.getUuid() + " already exists");
        } else {
            insertResume(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume" + uuid + " does not exist");
            return;
        }
        storage[index] = null;
        deleteResume(index);
        size--;
    }

    public int size() {
        return size;
    }
}
