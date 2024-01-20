package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doClear() {
        resumeList.clear();
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        resumeList.set((Integer) searchKey, resume);
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        resumeList.add(resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeList.get((Integer) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeList.remove(((Integer) searchKey).intValue());
    }

    @Override
    protected List<Resume> doGetAll() {
        Collections.sort(resumeList,resumeComparator);
        return resumeList;
    }

    @Override
    protected int doGetSize() {
        return resumeList.size();
    }

    @Override
    protected Object getSearchKey(String uuid) {
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
