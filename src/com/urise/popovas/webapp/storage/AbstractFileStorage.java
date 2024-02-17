package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private static final String DIRECTORY_PATH = "D:\\ResumeArchive";

    @Override
    protected File getSearchKey(String uuid) {
        return new File(getFilePath(uuid));
    }

    @Override
    protected void doClear() {
        File file = new File(DIRECTORY_PATH);
        File[] files = file.listFiles();
        for (File resumeFile : files) {
            resumeFile.delete();
        }
    }

    @Override
    protected void doUpdate(File searchKey, Resume resume) {
        searchKey.delete();
        writeFile(searchKey, resume);
    }

    @Override
    protected void doSave(File searchKey, Resume resume) {
        writeFile(searchKey, resume);
    }

    @Override
    protected Resume doGet(File searchKey) {
        return readFile(searchKey);
    }

    @Override
    protected void doDelete(File searchKey) {
        searchKey.delete();
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>();
        File file = new File(DIRECTORY_PATH);
        File[] files = file.listFiles();
        for (File resumeFile : files) {
            if(!resumeFile.isDirectory()) resumeList.add(readFile(resumeFile));
        }
        return resumeList;
    }

    @Override
    protected int doGetSize() {
        int count = 0;
        File file = new File(DIRECTORY_PATH);
        File[] files = file.listFiles();
        for (File resumeFile : files) {
            if(!resumeFile.isDirectory()) count++;
        }
        return count;
    }

    @Override
    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    protected abstract String getFileName(String uuid);

    protected String getFilePath(String uuid) {
        return DIRECTORY_PATH + getFileName(uuid);
    }

    protected abstract void writeFile(File file, Resume resume);

    protected abstract Resume readFile(File file);
}
