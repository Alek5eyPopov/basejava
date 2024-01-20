package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.ExistStorageException;
import com.urise.popovas.webapp.exception.NotExistStorageException;
import com.urise.popovas.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected final Comparator<Resume> resumeComparator = (Resume r1, Resume r2) ->{
        int res = 0;
//        if (r1 == null && r2 == null) return 0;
        if (r1 == null) return 1;
        if (r2 == null) return -1;
        if(r1.getFullName() != null && r2.getFullName() != null) res = r1.getFullName().compareTo(r2.getFullName());
        return res != 0 ? res : r1.getUuid().compareTo(r2.getUuid());
    };

    protected abstract Object getSearchKey(String uuid);

    protected abstract void doClear();

    protected abstract void doUpdate(Object searchKey, Resume resume);

    protected abstract void doSave(Object searchKey, Resume resume);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract List<Resume> doGetAll();

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
    public List<Resume> getAllSorted() {
        return doGetAll();
    }

    @Override
    public int size() {
        return doGetSize();
    }


}
