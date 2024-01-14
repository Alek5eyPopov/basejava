package com.urise.popovas.webapp.exception;

public class ExistStorageException extends StorageException {
    public ExistStorageException(String uuid) {
        super("ERROR: resume" + uuid + " already exists", uuid);
    }
}
