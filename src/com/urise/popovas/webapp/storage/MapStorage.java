package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

public class MapStorage extends AbstractMapStorage {
//    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        resumeMap.put((String) searchKey, resume);
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        resumeMap.put((String) searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeMap.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return resumeMap.containsKey(searchKey);
    }
}
