package com.urise.popovas.webapp.model;

public enum SectionType {
    CONTACTS("Контакты"),
    PERSONAL("Личные качества"),
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

    private String title;

    SectionType(String title) {
        this.title = title;
    }
}
