package com.urise.popovas.webapp.storage;


import com.urise.popovas.webapp.storage.serializer.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(new DataStreamSerializer(), DIRECTORY));
    }
}
