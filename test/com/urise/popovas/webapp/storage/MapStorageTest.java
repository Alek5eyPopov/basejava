package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;
import org.junit.Assert;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    public void getAll() {
        // Невозможно предусмотреть в каком именно порядке будут резюме в массиве из MAPы, поэтому метод оставил таким
        Resume[] expected = {new Resume("uuid1"), new Resume("uuid2"), new Resume("uuid3")};
        Assert.assertEquals(expected.length, storage.getAll().length);
    }
}

