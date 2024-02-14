package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage <String>{
    private final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doUpdate(String searchKey, Resume resume) {
        resumeMap.put(searchKey, resume);
    }

    @Override
    protected void doSave(String searchKey, Resume resume) {
        resumeMap.put(searchKey, resume);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return resumeMap.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    protected boolean isExist(String searchKey) {
        return resumeMap.containsKey(searchKey);
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
