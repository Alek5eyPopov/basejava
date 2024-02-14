package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage <Resume>{
    private final Map<String, Resume> resumeMap = new HashMap<>();
    
    @Override
    protected Resume getSearchKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void doUpdate(Resume searchKey, Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume searchKey, Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected void doDelete(Resume searchKey) {
        resumeMap.remove(searchKey.getUuid());
    }

    @Override
    protected boolean isExist(Resume searchKey) {
        return searchKey != null;
    }
    @Override
    protected void doClear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    protected int doGetSize() {
        return resumeMap.size();
    }
}
