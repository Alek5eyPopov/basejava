package com.urise.popovas.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume {
    private final String uuid;
    private String fullName;
    private final Map<SectionType, Section> sections = new HashMap<>();

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

    abstract class Section<CT, V1, V2, V3, V4> {
        protected final CT content;

        public Section(CT content) {
            this.content = content;
        }

        public CT getContent() {
            return content;
        }

        protected abstract void add(V1 value1, V2 value2, V3 value3, V4 value4);
    }

    class ContactSection extends Section<HashMap, ContactType, String, String, String> {
        public ContactSection() {
            super(new HashMap<ContactType, String>());
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            content.forEach((key, value) -> {
                sb.append(key + " : " + value + "\n");
            });
            return sb.toString();
        }

        @Override
        protected void add(ContactType value1, String value2, String value3, String value4) {
            content.put(value1, value2);
        }
    }

    class TextSection extends Section<List, String, String, String, String> {
        public TextSection() {
            super(new ArrayList<String>());
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            for (String str : (ArrayList<String>) content) {
                sb.append(str + "\n");
            }
            return sb.toString();
        }

        @Override
        protected void add(String value1, String value2, String value3, String value4) {
            content.add(value1);
        }
    }

    class Experience {
        private String period;
        private String company;
        private String position;
        private List<String> responsibilities;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(period + "\n");
            sb.append(company + "\n");
            sb.append(position + "\n");
            if (responsibilities != null) {
                for (String str : responsibilities) {
                    sb.append(str + "\n");
                }
            }
            return sb.toString();
        }

        public Experience(String period, String company, String position, List<String> responsibilities) {
            this.period = period;
            this.company = company;
            this.position = position;
            this.responsibilities = responsibilities;
        }
    }

    class ExperienceSection extends Section<List, String, String, String, List> {
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            for (Experience exp : (ArrayList<Experience>) content) {
                sb.append(exp.toString() + "\n");
            }
            return sb.toString();
        }

        @Override
        protected void add(String value1, String value2, String value3, List value4) {
            Experience experience = new Experience(value1, value2, value3, value4);
            content.add(experience);
        }

        public ExperienceSection() {
            super(new ArrayList<Experience>());
        }
    }

    public void addContact(ContactType contactType, String value) {
        if (!sections.containsKey(SectionType.CONTACTS)) sections.put(SectionType.CONTACTS, new ContactSection());

        Section section = sections.get(SectionType.CONTACTS);
        section.add(contactType, value, null, null);
    }

    public void addTextSectionData(SectionType sectionType, String value) {
        if (!sections.containsKey(sectionType)) sections.put(sectionType, new TextSection());
        Section section = sections.get(sectionType);
        section.add(value, null, null, null);
    }

    public void addExpSectionData(SectionType sectionType, String period, String company, String position, List responsibilities) {
        if (!sections.containsKey(sectionType)) sections.put(sectionType, new ExperienceSection());
        Section section = sections.get(sectionType);
        section.add(period, company, position, responsibilities);
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
        sections.forEach((key, value) -> {
            sb.append(key + "\n" + value + "\n");
        });

        return sb.toString();
    }

}
