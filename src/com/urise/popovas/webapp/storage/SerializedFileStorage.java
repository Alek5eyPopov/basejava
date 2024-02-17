package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.io.*;

public class SerializedFileStorage extends AbstractFileStorage {
    @Override
    protected String getFileName(String uuid) {
        return "\\Resume" + uuid + ".bin";
    }

    @Override
    protected void writeFile(File file, Resume resume) {
        try (FileOutputStream fos = new FileOutputStream(file.getPath());
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(resume);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Resume readFile(File file) {
        try (FileInputStream fis = new FileInputStream(file.getPath());
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Resume) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
