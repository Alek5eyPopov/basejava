package com.urise.popovas.webapp.storage.serializer;

import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;

import java.io.*;

public class ObjectSerializer implements Serializer{
    @Override
    public void writeFile(OutputStream os, Resume resume) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(resume);
        oos.close();
    }

    @Override
    public Resume readFile(InputStream is) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Read file error", null, e);
        }
    }
}
