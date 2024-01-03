package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.ExistStorageException;
import com.urise.popovas.webapp.exception.NotExistStorageException;
import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AbstractArrayStorageTest {
    private final Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    public AbstractArrayStorageTest() {
        this.storage = null;
    }

    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";
    private final static String DUMMY = "dummy";
    private final static String SAVE_UUID = "SaveUuid";

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(storage.size(), 0);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        Assert.assertEquals(storage.get(UUID_2), resume);
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_2), storage.get(UUID_2));
    }

    @Test
    public void getAll() {
        Resume[] storageExp = {new Resume(UUID_1), new Resume(UUID_2), new Resume(UUID_3)};
        Resume[] storageAct = storage.getAll();
        Assert.assertArrayEquals(storageExp, storageAct);
    }

    @Test
    public void save() {
        Resume resume = new Resume(SAVE_UUID);
        storage.save(resume);
        Assert.assertEquals(storage.size(), 4);
        Assert.assertEquals(resume, storage.get(SAVE_UUID));
    }

    @Test
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertEquals(storage.size(), 2);
        try {
            storage.get(UUID_2);
            fail();
        } catch (NotExistStorageException ex) {
        }
        Assert.assertEquals(storage.size(), 2);
    }

    @Test
    public void size() {
        Assert.assertEquals(storage.size(), 3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume(DUMMY));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT ; i++) {
                storage.save(new Resume("UUID_" + i));
            }
        } catch (StorageException ex) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        try {
            storage.save(new Resume(DUMMY));
            Assert.fail();
        } catch (StorageException ex) {
        }
    }
}

