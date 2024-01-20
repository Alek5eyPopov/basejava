package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[ArrayStorage.STORAGE_LIMIT];
    protected int size;

    protected abstract void insertResume(Resume resume, int ind);

    protected abstract void deleteResume(int ind);

    @Override
    protected void doClear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected boolean isExist(Object searchKey) { return (Integer) searchKey >= 0; }

    @Override
    protected void doDelete(Object searchKey) {
        storage[(Integer) searchKey] = null;
        deleteResume((Integer) searchKey);
        size--;
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(storage, 0, size)));
        Collections.sort(resumeList,resumeComparator);
        return resumeList;
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("ERROR: impossible to save resume " + resume.getUuid() + ". Array overflow", resume.getUuid());
        } else {
            insertResume(resume, (Integer) searchKey);
            size++;
        }
    }

    @Override
    protected int doGetSize() {
        return size;
    }
}
