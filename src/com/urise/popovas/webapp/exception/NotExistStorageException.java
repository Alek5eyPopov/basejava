package com.urise.popovas.webapp.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("ERROR: resume" + uuid + " does not exist", uuid);
    }
}
