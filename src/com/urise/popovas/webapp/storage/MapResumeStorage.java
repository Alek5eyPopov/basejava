package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

public class MapResumeStorage extends AbstractMapStorage{
    @Override
    protected Object getSearchKey(String uuid) {
        return resumeMap.get(uuid);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Object searchKey, Resume resume) {
        resumeMap.put(resume.getUuid(), resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return (Resume)searchKey;
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove(((Resume)searchKey).getUuid());
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return searchKey != null;
    }
}
