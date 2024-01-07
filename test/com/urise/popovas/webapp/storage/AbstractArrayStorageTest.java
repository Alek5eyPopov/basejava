package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.ExistStorageException;
import com.urise.popovas.webapp.exception.NotExistStorageException;
import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AbstractArrayStorageTest {
    private final Storage storage;
    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";
    private final static String DUMMY = "dummy";
    private final static String SAVE_UUID = "SaveUuid";
    private final static Resume RESUME_1 = new Resume(UUID_1);
    private final static Resume RESUME_2 = new Resume(UUID_2);
    private final static Resume RESUME_3 = new Resume(UUID_3);
    private final static Resume RESUME_SAVE = new Resume(SAVE_UUID);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    public AbstractArrayStorageTest() {
        this.storage = null;
    }

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
        Assert.assertTrue(assertSize(0));
        Assert.assertArrayEquals(storage.getAll(), new Resume[0]);
    }

    @Test
    public void update() {
        storage.update(RESUME_2);
        Assert.assertEquals(storage.get(UUID_2), RESUME_2);
    }

    @Test
    public void get() {
        Assert.assertTrue(assertGet(RESUME_1));
        Assert.assertTrue(assertGet(RESUME_2));
        Assert.assertTrue(assertGet(RESUME_3));
    }

    @Test
    public void getAll() {
        Resume[] expected = {RESUME_1, RESUME_2, RESUME_3};
        Assert.assertArrayEquals(expected, storage.getAll());
    }

    @Test
    public void save() {
        storage.save(RESUME_SAVE);
        Assert.assertTrue(assertSize(4));
        Assert.assertTrue(assertGet(RESUME_SAVE));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        Assert.assertTrue(assertSize(2));
        storage.get(UUID_2);
    }

    @Test
    public void size() {
        Assert.assertTrue(assertSize(3));
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
        storage.save(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("UUID_" + i));
            }
        } catch (StorageException ex) {
            Assert.fail("Переполнение произошло раньше времени");
        }
        storage.save(new Resume(DUMMY));
    }

    private boolean assertSize(int size) {
        return size == storage.size();
    }

    private boolean assertGet(Resume resume) {
        return resume.equals(storage.get(resume.getUuid()));
    }
}

