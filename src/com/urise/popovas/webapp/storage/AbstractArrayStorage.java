package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[ArrayStorage.STORAGE_LIMIT];
    protected int size;

    protected abstract void insertResume(Resume resume, int ind);

    protected abstract void deleteResume(int ind);

    @Override
    protected void clearing() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void updating(int index, Resume resume) {
        storage[index] = resume;
    }

    @Override
    protected Resume geting(int index) {
        return storage[index];
    }

    @Override
    protected void deleting(int index) {
        storage[index] = null;
        deleteResume(index);
        size--;
    }

    @Override
    protected Resume[] gettingAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    protected void saveing(int index, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("ERROR: impossible to save resume " + resume.getUuid() + ". Array overflow", resume.getUuid());
        } else {
            insertResume(resume, index);
            size++;
        }
    }

    @Override
    protected int getingSize() {
        return size;
    }
}
