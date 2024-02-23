package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.exception.StorageException;
import com.urise.popovas.webapp.model.Resume;

import java.io.*;

public class SerializedFileStorage extends AbstractFileStorage {
    @Override
    protected String getFileName(String uuid) {
        return "\\Resume" + uuid + ".bin";
    }

    @Override
    protected void writeFile(File file, Resume resume) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file.getPath())));
        oos.writeObject(resume);
        oos.close();
    }

    @Override
    protected Resume readFile(File file) throws IOException{
        try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file.getPath())))) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("Read file error" + file.getPath(), null, e);
        }
    }
}
