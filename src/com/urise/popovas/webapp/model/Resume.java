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

    abstract class Section {
    }

    class TextSection extends Section {
        private String text;

        public TextSection(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public TextSection() {
        }

        @Override
        public String toString() {
            return text;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TextSection that = (TextSection) o;
            return Objects.equals(text, that.text);
        }

        @Override
        public int hashCode() {
            return Objects.hash(text);
        }
    }

    class ListSection extends Section {
        private List<String> list = new ArrayList<>();

        public ListSection() {
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            for (String str : list) {
                sb.append(str + "\n");
            }
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ListSection that = (ListSection) o;
            return Objects.equals(list, that.list);
        }

        @Override
        public int hashCode() {
            return Objects.hash(list);
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }

        public ListSection(List<String> list) {
            this.list = list;
        }
    }

    class Period {
        private LocalDate begin;
        private LocalDate end;
        private String tittle;
        private String description;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(begin.getMonthValue() +"/" + begin.getYear() + " - " + end.getMonthValue() +"/" + end.getYear() + "\n");
            if (tittle != null && tittle != "") {
                sb.append(tittle + "\n");
            }
            sb.append(description + "\n");
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Period period = (Period) o;
            return Objects.equals(begin, period.begin) &&
                    Objects.equals(end, period.end) &&
                    Objects.equals(tittle, period.tittle) &&
                    Objects.equals(description, period.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(begin, end, tittle, description);
        }

        public LocalDate getBegin() {
            return begin;
        }

        public void setBegin(LocalDate begin) {
            this.begin = begin;
        }

        public LocalDate getEnd() {
            return end;
        }

        public void setEnd(LocalDate end) {
            this.end = end;
        }

        public String getTittle() {
            return tittle;
        }

        public void setTittle(String tittle) {
            this.tittle = tittle;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Period() {
        }

        public Period(LocalDate begin, LocalDate end, String description) {
            this.begin = begin;
            this.end = end;
            this.description = description;
        }

        public Period(LocalDate begin, LocalDate end, String tittle, String description) {
            this.begin = begin;
            this.end = end;
            this.tittle = tittle;
            this.description = description;
        }
    }

    class CompanySection extends Section{
        private final List<Company> companyList = new ArrayList<>();

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            for (Company comp: companyList) {
                sb.append(comp + "\n");
            }
            return  sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CompanySection that = (CompanySection) o;
            return Objects.equals(companyList, that.companyList);
        }

        @Override
        public int hashCode() {
            return Objects.hash(companyList);
        }

        public List<Company> getCompanyList() {
            return companyList;
        }

        public CompanySection() {
        }
    }
    class Company{

        String name;
        String website;
        List<Period> periods = new ArrayList<>();

        public Company(String name, String website, List<Period> periods) {
            this.name = name;
            this.website = website;
            this.periods = periods;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Company that = (Company) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(website, that.website) &&
                    Objects.equals(periods, that.periods);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, website, periods);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public List<Period> getPeriods() {
            return periods;
        }

        public void setPeriods(List<Period> periods) {
            this.periods = periods;
        }

        public Company() {
        }

        public Company(String name, String website) {
            this.name = name;
            this.website = website;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(name + "\n");
            if( website != null && website != "") {
                sb.append(website + "\n");
            }
            for (Period per : periods) {
                sb.append(per.toString() + "\n");
            }
            return sb.toString();
        }


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
