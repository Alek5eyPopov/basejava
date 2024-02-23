package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
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
        File dir = new File(DIRECTORY_PATH);
        File[] files = dir.listFiles();
        if (files == null) throw new StorageException("No files in directory: " + DIRECTORY_PATH);
        for (File file : files) {
            doDelete(file);
        }
    }

    @Override
    protected void doUpdate(File searchKey, Resume resume) {
        doDelete(searchKey);
        try {
            writeFile(searchKey, resume);
        } catch (IOException e) {
            throw new StorageException("Update file error " + searchKey.getPath(), null, e);
        }
    }

    @Override
    protected void doSave(File searchKey, Resume resume) {
        try {
            writeFile(searchKey, resume);
        } catch (IOException e) {
            throw new StorageException("Save file error " + searchKey.getPath(), null, e);
        }
    }

    @Override
    protected Resume doGet(File searchKey) {
        try {
            return readFile(searchKey);
        } catch (IOException e) {
            throw new StorageException("Read file error " + searchKey.getPath(), null, e);
        }
    }

    @Override
    protected void doDelete(File searchKey) {
        if(!searchKey.delete()){
            throw new StorageException("File " + searchKey.getPath() + "was not deleted");
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        List<Resume> resumeList = new ArrayList<>();
        File dir = new File(DIRECTORY_PATH);
        File[] files = dir.listFiles();
        if (files == null) throw new StorageException("No files in directory: " + DIRECTORY_PATH);
        for (File file : files) {
            if (!file.isDirectory()) {
                resumeList.add(doGet(file));
            }
        }
        return resumeList;
    }

    @Override
    protected int doGetSize() {
        File file = new File(DIRECTORY_PATH);
        File[] files = file.listFiles();
        if (files == null) throw new StorageException("No files in directory: " + DIRECTORY_PATH);
        return files.length;
    }

    @Override
    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    protected abstract String getFileName(String uuid);

    protected String getFilePath(String uuid) {
        return DIRECTORY_PATH + getFileName(uuid);
    }

    protected abstract void writeFile(File file, Resume resume) throws IOException;

    protected abstract Resume readFile(File file) throws IOException;
}
