package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doClear() {
        resumeList.clear();
    }

    @Override
    protected void doUpdate(Integer searchKey, Resume resume) {
        resumeList.set(searchKey, resume);
    }

    @Override
    protected void doSave(Integer searchKey, Resume resume) {
        resumeList.add(resume);
    }

    @Override
    protected Resume doGet(Integer searchKey) {
        return resumeList.get(searchKey);
    }

    @Override
    protected void doDelete(Integer searchKey) {
        resumeList.remove((searchKey).intValue());
    }

    @Override
    protected List<Resume> doGetAll() {
        return resumeList;
    }

    @Override
    protected int doGetSize() {
        return resumeList.size();
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        int i;
        ListIterator<Resume> iterator = resumeList.listIterator();
        while (iterator.hasNext()) {
            i = iterator.nextIndex();
            if (uuid.equals(iterator.next().getUuid())) {
                return i;
            }
        }
        return null;
    }
}
