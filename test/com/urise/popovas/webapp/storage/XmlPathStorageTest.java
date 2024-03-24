package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.storage.serializer.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(new XmlStreamSerializer(), DIRECTORY));
    }
}
