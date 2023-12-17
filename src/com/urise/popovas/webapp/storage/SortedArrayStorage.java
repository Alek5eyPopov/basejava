package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends ArrayStorage {

    @Override
    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= storage.length) {
            System.out.println("ERROR: impossible to save resume " + resume.getUuid() + ". Array overflow");
        } else if (index >= 0) {
            System.out.println("ERROR: resume" + resume.getUuid() + " already exists");
        } else {
            index = -index - 1;
            if (index == size) {
                storage[size] = resume;
            } else {
                Resume[] resumes = Arrays.copyOfRange(storage, index, size);
                storage[index] = resume;
                index++;
                for (int i = 0; i < resumes.length; i++) {
                    storage[index + i] = resumes[i];
                }
            }
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: resume" + uuid + " does not exist");
            return;
        }
        storage[index] = null;
        for (int i = index + 1; i < size; i++) {
            storage[i - 1] = storage[i];
        }
        size--;
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
