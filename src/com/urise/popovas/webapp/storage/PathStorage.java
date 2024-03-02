package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;
import com.urise.popovas.webapp.storage.serializer.Serializer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private Serializer serializer;
    private Path storageDirectory;


    public PathStorage(Serializer serializer, String directory) {
        Objects.requireNonNull(directory, "Directory must not be null");
        this.serializer = serializer;
        storageDirectory = Paths.get(directory);

        if (!Files.isDirectory(storageDirectory)) {
            throw new StorageException(storageDirectory.getFileName() + " is not directory");
        }
        if (!Files.isWritable(storageDirectory)) {
            throw new StorageException(storageDirectory.getFileName() + " no access to write");
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return storageDirectory.resolve(uuid);
    }

    @Override
    protected void doClear() {
        getStorageDirPaths().forEach(this::doDelete);
    }

    @Override
    protected void doUpdate(Path searchKey, Resume resume) {
        try {
            serializer.writeFile(new BufferedOutputStream(Files.newOutputStream(searchKey)),resume);
        } catch (IOException e) {
            throw new StorageException("Update file error " + searchKey.getFileName(), null, e);
        }
    }

    @Override
    protected void doSave(Path searchKey, Resume resume) {
        try {
            Files.createFile(searchKey);
        } catch (IOException e) {
            throw new StorageException("Save file error " + searchKey.getFileName(), null, e);
        }
        doUpdate(searchKey, resume);
    }

    @Override
    protected Resume doGet(Path searchKey) {
        try {
            return serializer.readFile(new BufferedInputStream(Files.newInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("Read file error " + searchKey.getFileName(), null, e);
        }
    }

    @Override
    protected void doDelete(Path searchKey) {
        try {
            Files.delete(searchKey);
        } catch (IOException e) {
            throw new StorageException("File delete error " + searchKey.getFileName());
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        return getStorageDirPaths().map(this::doGet).collect(Collectors.toList());
    }

    @Override
    protected int doGetSize() {
        return (int) getStorageDirPaths().count();
    }

    @Override
    protected boolean isExist(Path searchKey) {
        return Files.exists(searchKey);
    }

    private Stream<Path> getStorageDirPaths() {
        try {
            return Files.list(storageDirectory);
        } catch (IOException e) {
            throw new StorageException("Get Storage directory paths list error");
        }
    }
}
