package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.ResumeTestData;
import com.urise.popovas.webapp.exception.ExistStorageException;
import com.urise.popovas.webapp.exception.NotExistStorageException;
import com.urise.popovas.webapp.model.Resume;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbstractStorageTest {
    protected final Storage storage;
    private final static String UUID_1 = "uuid1";
    private final static String UUID_2 = "uuid2";
    private final static String UUID_3 = "uuid3";
    private final static String NAME_1 = "full name 1";
    private final static String NAME_2 = "full name 2";
    private final static String NAME_3 = "full name 3";
    protected final static String DUMMY = "dummy";
    private final static String SAVE_UUID = "SaveUuid";
    private final static String SAVE_NAME = "SaveName";
    private final static Resume RESUME_1 = ResumeTestData.fillResume(UUID_1, NAME_1);
    private final static Resume RESUME_2 = ResumeTestData.fillResume(UUID_2,NAME_2);
    private final static Resume RESUME_3 = ResumeTestData.fillResume(UUID_3,NAME_3);
    private final static Resume RESUME_SAVE = ResumeTestData.fillResume(SAVE_UUID, SAVE_NAME);
    protected final static String DIRECTORY = "D:\\ResumeArchive";

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    public AbstractStorageTest() {
        this.storage = null;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @After
    public void afterClear(){
        storage.clear();
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        assertListEquals(storage.getAllSorted(), new ArrayList<>());
    }

    @Test
    public void update() {
        storage.update(RESUME_2);
        Assert.assertEquals(storage.get(UUID_2), RESUME_2);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getAll() {
        List<Resume> expected = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
        assertListEquals(storage.getAllSorted(), expected);
    }

    @Test
    public void save() {
        storage.save(RESUME_SAVE);
        assertSize(4);
        assertGet(RESUME_SAVE);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(DUMMY);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(ResumeTestData.fillResume(DUMMY, "Dummy"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(DUMMY);
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        Assert.assertEquals(resume, storage.get(resume.getUuid()));
    }

    private void assertListEquals(List<Resume> act, List<Resume> exp) {
        Assert.assertTrue(act.size() == exp.size() && act.containsAll(exp) && exp.containsAll(act));
    }
}
