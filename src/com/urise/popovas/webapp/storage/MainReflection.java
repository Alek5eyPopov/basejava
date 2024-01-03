package com.urise.popovas.webapp.storage;

import com.urise.popovas.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) {
        Resume resume = new Resume();
        try {
            Method method = resume.getClass().getMethod("toString", null);
            System.out.println(method.invoke(resume, null));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
