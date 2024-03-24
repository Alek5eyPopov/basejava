package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.storage.serializer.ObjectSerializer;

import java.io.File;

public class FileStorageTest extends AbstractStorageTest{
    public FileStorageTest() {
        super(new FileStorage(new ObjectSerializer(), new File(DIRECTORY)));
    }
}
