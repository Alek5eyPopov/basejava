package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> resumeList = new ArrayList<>();

    @Override
    protected void clearing() {
        resumeList.clear();
    }

    @Override
    protected void updating(int index, Resume resume) {
        resumeList.set(index, resume);
    }

    @Override
    protected void saveing(int index, Resume resume) {
        resumeList.add(resume);
    }

    @Override
    protected Resume geting(int index) {
        return resumeList.get(index);
    }

    @Override
    protected void deleting(int index) {
        resumeList.remove(index);
    }

    @Override
    protected Resume[] gettingAll() {
        return resumeList.toArray(new Resume[resumeList.size()]);
    }

    @Override
    protected int getingSize() {
        return resumeList.size();
    }

    @Override
    protected int getIndex(String uuid) {
        int i;
        ListIterator<Resume> iterator = resumeList.listIterator();
        while (iterator.hasNext()) {
            i = iterator.nextIndex();
            if (uuid.equals(iterator.next().getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
