package com.urise.popovas.webapp.model;

import java.io.Serializable;

public enum SectionType implements Serializable {
    OBJECTIVE("Позиция"),
    PERSONAL("Личные качества"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    public String getTitle() {
        return title;
    }

//    @Override
//    public String toString() {
//        return title;
//    }

    private String title;

    SectionType(String title) {
        this.title = title;
    }
}
