package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.*;

public abstract class AbstractMapStorage extends AbstractStorage{
    protected final Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected void doClear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>(resumeMap.values());
        Collections.sort(resumeList,resumeComparator);
        return resumeList;
    }

    @Override
    protected int doGetSize() {
        return resumeMap.size();
    }

}
