package com.urise.popovas.webapp.model;

public enum ContactType {
    TELEPHONE("Телефон"),
    SKYPE("Скайп"),
    EMAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");


    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

    private String title;
}
