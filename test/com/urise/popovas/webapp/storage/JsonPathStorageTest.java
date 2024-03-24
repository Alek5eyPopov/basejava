package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(new JsonStreamSerializer(), DIRECTORY));
    }
}
