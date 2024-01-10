package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.ExistStorageException;
import com.urise.popovas.webapp.exception.NotExistStorageException;
import com.urise.popovas.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract int getIndex(String uuid);
    protected abstract void clearing();
    protected abstract void updating(int index, Resume resume);
    protected abstract void saveing(int index, Resume resume);
    protected abstract Resume geting(int index);
    protected abstract void deleting(int index);
    protected abstract Resume[] gettingAll();
    protected abstract int getingSize();

    protected void checkExist(int index, String uuid) {
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
    }

    protected void checkNotExist(int index, String uuid) {
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
    }

    @Override
    public void clear() {
        clearing();
    }

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        checkExist(index, uuid);
        updating(index, resume);
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        checkNotExist(index, uuid);
        saveing(index, resume);
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        checkExist(index, uuid);
        return geting(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        checkExist(index, uuid);
        deleting(index);
    }

    @Override
    public Resume[] getAll() {
        return gettingAll();
    }

    @Override
    public int size() {
        return getingSize();
    }


}
