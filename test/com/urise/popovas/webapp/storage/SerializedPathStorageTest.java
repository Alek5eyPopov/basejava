package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.storage.Serializer.ObjectSerializer;

public class SerializedPathStorageTest extends AbstractStorageTest{
    public SerializedPathStorageTest() {
        super(new SerializedPathStorage(new ObjectSerializer(), "D:\\ResumeArchive"));
    }
}
