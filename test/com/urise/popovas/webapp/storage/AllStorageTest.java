package com.urise.popovas.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        SortedArrayStorageTest.class,
        MapResumeStorageTest.class
})
public class AllStorageTest {
}
