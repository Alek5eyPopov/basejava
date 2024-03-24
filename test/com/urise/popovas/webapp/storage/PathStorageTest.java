package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.storage.serializer.ObjectSerializer;

public class PathStorageTest extends AbstractStorageTest{
    public PathStorageTest() {
        super(new PathStorage(new ObjectSerializer(), DIRECTORY ));
    }
}
