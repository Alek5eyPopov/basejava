package com.urise.popovas.webapp.storage.Serializer;

import com.urise.popovas.webapp.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {
    void writeFile(OutputStream os, Resume resume) throws IOException;

    Resume readFile(InputStream is) throws IOException;
}
