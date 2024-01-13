package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.ExistStorageException;
import com.urise.popovas.webapp.exception.NotExistStorageException;
import com.urise.popovas.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract Object getSearchKey(String uuid);

    protected abstract void doClear();

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume[] doGetAll();

    protected abstract int doGetSize();

    protected abstract boolean isExist(Object searchKey);

    protected Object getExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected Object getNotExistingSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    @Override
    public void clear() {
        doClear();
    }

    @Override
    public void update(Resume resume) { doUpdate(getExistingSearchKey(resume.getUuid()), resume); }

    @Override
    public void save(Resume resume) { doSave(getNotExistingSearchKey(resume.getUuid()), resume); }

    @Override
    public Resume get(String uuid) { return doGet(getExistingSearchKey(uuid)); }

    @Override
    public void delete(String uuid) { doDelete(getExistingSearchKey(uuid)); }

    @Override
    public Resume[] getAll() {
        return doGetAll();
    }

    @Override
    public int size() {
        return doGetSize();
    }


}
