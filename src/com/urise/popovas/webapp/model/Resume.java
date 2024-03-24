package com.urise.popovas.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {
    private final static long serialVersionUID = 1L;
    private final String uuid;
    private String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

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

//        section.getCompanyList().add(new Company(name, website, periodList));

        Company company = new Company(name, website, periodList);
        Link link = new Link(name, website);
        Map<Link, List<Company>> companyMap = section.getCompanyMap();
        if (!companyMap.containsKey(link)) companyMap.put(link, new ArrayList<>());
        List<Company> companyList = companyMap.get(link);
        companyList.add(company);
    }

    public void addCompSectionData(SectionType sectionType, String name, String website, LocalDate begin,
                                   LocalDate end, String description) {
        if (!sections.containsKey(sectionType)) sections.put(sectionType, new CompanySection());
        CompanySection section = (CompanySection)sections.get(sectionType);
        List<Period> periodList = new ArrayList<>();
        periodList.add(new Period(begin,end,"",description));

//        section.getCompanyList().add(new Company(name, website, periodList));

        Company company = new Company(name, website, periodList);
        Link link = new Link(name, website);
        Map<Link, List<Company>> companyMap = section.getCompanyMap();
        if (!companyMap.containsKey(link)) companyMap.put(link, new ArrayList<>());
        List<Company> companyList = companyMap.get(link);
        companyList.add(company);
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
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(sections, resume.sections) &&
                Objects.equals(contacts, resume.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, sections, contacts);
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

    public String getContact(ContactType type) {
        return contacts.get(type);
    }

    public Section getSection(SectionType type) {
        return sections.get(type);
    }
}
