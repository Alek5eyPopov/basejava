package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.ExistStorageException;
import com.urise.popovas.webapp.exception.NotExistStorageException;
import com.urise.popovas.webapp.model.Resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    public static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected final Comparator<Resume> resumeComparator = Comparator.comparing(Resume::getUuid).thenComparing(Resume::getFullName);

    protected SK getExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        } else {
            LOG.warning("ERROR: resume" + uuid + " does not exist");
            throw new NotExistStorageException(uuid);
        }
    }

    protected SK getNotExistingSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("ERROR: resume" + uuid + " already exists");
            throw new ExistStorageException(uuid);
        } else {
            return searchKey;
        }
    }

    @Override
    public void clear() {
        LOG.info("clear");
        doClear();
    }

    @Override
    public void update(Resume resume) {
        LOG.info("update" + resume);
        doUpdate(getExistingSearchKey(resume.getUuid()), resume);
    }

    @Override
    public void save(Resume resume) {
        LOG.info("save" + resume);
        doSave(getNotExistingSearchKey(resume.getUuid()), resume);
    }

    @Override
    public Resume get(String uuid) {
        LOG.info("get" + uuid);
        return doGet(getExistingSearchKey(uuid));
    }

    @Override
    public void delete(String uuid) {
        LOG.info("delete" + uuid);
        doDelete(getExistingSearchKey(uuid));
    }

    @Override
    public List<Resume> getAllSorted() {
        LOG.info("getAll");
        List<Resume> resumeList = doGetAll();
        Collections.sort(resumeList,resumeComparator);
        return resumeList;
    }

    @Override
    public int size() {
        LOG.info("size");
        return doGetSize();
    }

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doClear();

    protected abstract void doUpdate(SK searchKey, Resume resume);

    protected abstract void doSave(SK searchKey, Resume resume);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract List<Resume> doGetAll();

    protected abstract int doGetSize();

    protected abstract boolean isExist(SK searchKey);

}
