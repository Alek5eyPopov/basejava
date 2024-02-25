package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.storage.Serializer.ObjectSerializer;

import java.io.File;

public class SerializedFileStorageTest extends AbstractStorageTest{
    public SerializedFileStorageTest() {
        super(new SerializedFileStorage(new ObjectSerializer(), new File("D:\\ResumeArchive")));
    }
}
