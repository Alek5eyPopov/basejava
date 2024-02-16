package com.urise.popovas.webapp.model;

import java.time.LocalDate;
import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private String fullName;
    private final Map<SectionType, Section> sections = new HashMap<>();
    private final Map<ContactType, String> contacts = new HashMap<>();

    public Resume(String fullName) {
        this.fullName = fullName;
        this.uuid = UUID.randomUUID().toString();
    }

    public Resume() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void addContact(ContactType contactType, String value) {
        contacts.put(contactType, value);
    }

    public void addTextSectionData(SectionType sectionType, String value) {
        sections.put(sectionType, new TextSection(value));
    }

    public void addListSectionData(SectionType sectionType, String value) {
        if (!sections.containsKey(sectionType)) sections.put(sectionType, new ListSection());
        ListSection section = (ListSection )sections.get(sectionType);
        section.getList().add(value);
    }

    public void addCompSectionData(SectionType sectionType, String name, String website, LocalDate begin,
                                   LocalDate end, String tittle, String description) {
        if (!sections.containsKey(sectionType)) sections.put(sectionType, new CompanySection());
        CompanySection section = (CompanySection)sections.get(sectionType);
        List<Period> periodList = new ArrayList<>();
        periodList.add(new Period(begin,end,tittle,description));
        section.getCompanyList().add(new Company(name, website, periodList));
    }

    public void addCompSectionData(SectionType sectionType, String name, String website, LocalDate begin,
                                   LocalDate end, String description) {
        if (!sections.containsKey(sectionType)) sections.put(sectionType, new CompanySection());
        CompanySection section = (CompanySection)sections.get(sectionType);
        List<Period> periodList = new ArrayList<>();
        periodList.add(new Period(begin,end,"",description));
        section.getCompanyList().add(new Company(name, website, periodList));
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(fullName + "\n");
        sb.append(uuid + "\n");

        List<SectionType> sectionTypeList = new ArrayList<>(EnumSet.allOf(SectionType.class));
        for ( SectionType st: sectionTypeList ) {
            sections.get(st);
            sb.append(st + "\n" + sections.get(st) + "\n");
        }

        return sb.toString();
    }

}
