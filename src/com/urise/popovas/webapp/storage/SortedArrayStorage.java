package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends ArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void insertResume(Resume resume, int ind) {
        ind = -ind - 1;
        if (ind == size) {
            storage[size] = resume;
        } else {
            Resume[] resumes = Arrays.copyOfRange(storage, ind, size);
            storage[ind] = resume;
            ind++;
            for (int i = 0; i < resumes.length; i++) {
                storage[ind + i] = resumes[i];
            }
        }
    }

    @Override
    protected void deleteResume(int ind){
        storage[ind] = null;
        for (int i = ind + 1; i < size; i++) {
            storage[i - 1] = storage[i];
        }
    }
}
