package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;
import com.urise.popovas.webapp.storage.serializer.Serializer;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File storageDirectory;
    private Serializer serializer;

    public FileStorage(Serializer serializer, File directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        if (!directory.isDirectory()) {
            throw new StorageException(directory.getPath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new StorageException(directory.getPath() + " no access to write or read");
        }
        this.storageDirectory = directory;
        this.serializer = serializer;
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(storageDirectory, uuid);
    }

    @Override
    protected void doClear() {
        Arrays.stream(getStorageDirFiles()).forEach(this::doDelete);
    }

    @Override
    protected void doUpdate(File searchKey, Resume resume) {
        try {
            serializer.writeFile(new BufferedOutputStream(new FileOutputStream(searchKey.getPath())),resume);
        } catch (IOException e) {
            throw new StorageException("Update file error " + searchKey.getPath(), null, e);
        }

    }

    @Override
    protected void doSave(File searchKey, Resume resume) {
        try {
            searchKey.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Save file error " + searchKey.getPath(), null, e);
        }
        doUpdate(searchKey, resume);
    }

    @Override
    protected Resume doGet(File searchKey) {
        try {
            return serializer.readFile(new BufferedInputStream(new FileInputStream(searchKey.getPath())));
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
        Arrays.stream(getStorageDirFiles()).forEach(file -> resumeList.add(doGet(file)));
        return resumeList;
    }

    @Override
    protected int doGetSize() {
        return getStorageDirFiles().length;
    }

    @Override
    protected boolean isExist(File searchKey) {
        return searchKey.exists();
    }

    private File[] getStorageDirFiles() {
        File[] files = storageDirectory.listFiles();
        if (files == null) {
            throw new StorageException("No files in directory: " + storageDirectory.getPath());
        }
        return files;
    }
}
