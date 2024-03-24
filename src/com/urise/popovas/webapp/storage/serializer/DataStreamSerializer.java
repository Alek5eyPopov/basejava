package com.urise.popovas.webapp.storage.serializer;

import com.urise.popovas.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Serializer {

    @Override
    public void writeFile(OutputStream os, Resume r) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(entry.getValue());
                    }
            );

            Map<SectionType, Section> sections = r.getSections();

            writeCollection(dos, sections.entrySet(), entry -> { // Map<SectionType, Section>
                dos.writeUTF(entry.getKey().name());
                Section section = entry.getValue();
                switch (section.getClass().getSimpleName()) {
                    case ("TextSection"):
                        TextSection textSection = (TextSection) section;
                        dos.writeUTF(textSection.getText());
                        break;
                    case ("ListSection"):
                        ListSection listSection = (ListSection) section;
                        writeCollection(dos, listSection.getList(), dos::writeUTF); // List<String>
                        break;
                    case ("CompanySection"):
                        CompanySection companySection = (CompanySection) section;

                        writeCollection(dos, companySection.getCompanyMap().entrySet(), company -> { //  Map<Link,List<Company>>
                            dos.writeUTF(company.getKey().getName());
                            dos.writeUTF(company.getKey().getUrl());

                            List<Company> companyList = company.getValue(); // List<Company>
                            writeCollection(dos, companyList, comp -> {
                                List<Period> periods = comp.getPeriods();
                                writeCollection(dos, periods, period -> { // List<Period>
                                    writeLocalDate(dos, period.getBegin());
                                    writeLocalDate(dos, period.getEnd());
                                    dos.writeUTF(period.getTittle());
                                    dos.writeUTF(period.getDescription());
                                });
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume readFile(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readCollection(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

//            readCollection(dis, () -> {
//                String sectionName = dis.readUTF();
//                SectionType st = SectionType.valueOf(sectionName);
//                switch (sectionName) {
//                    case ("OBJECTIVE"):
//                    case ("PERSONAL"):
//                        resume.addTextSectionData(st, dis.readUTF());
//                        break;
//                    case ("ACHIEVEMENT"):
//                    case ("QUALIFICATIONS"):
//                        readCollection(dis, () -> resume.addListSectionData(st, dis.readUTF()));
//                        break;
//                    case ("EXPERIENCE"):
//                    case ("EDUCATION"):
//                        String name = dis.readUTF();
//                        String website = dis.readUTF();
//                        readCollection(dis, () -> readCollection(dis, () -> {
//                            LocalDate begin = readLocalDate(dis);
//                            LocalDate end = readLocalDate(dis);
//                            String tittle = dis.readUTF();
//                            String description = dis.readUTF();
//                            resume.addCompSectionData(st, name, website, begin, end, tittle, description);
//                        }));
//                        break;
//                }
//            });

            int size = dis.readInt();
            for (int i = 0; i < size; i++) { //  цикл по секциям Map<SectionType, Section> sections
                String sectionName = dis.readUTF();
                SectionType st = SectionType.valueOf(sectionName);
                switch (sectionName) {
                    case ("OBJECTIVE"):
                    case ("PERSONAL"):
                        resume.addTextSectionData(st, dis.readUTF());
                        break;
                    case ("ACHIEVEMENT"):
                    case ("QUALIFICATIONS"):
                        int listSize = dis.readInt();
                        for (int j = 0; j < listSize; j++) { // цикл по элементам секции List<String> list
                            resume.addListSectionData(st, dis.readUTF());
                        }
                        break;
                    case ("EXPERIENCE"):
                    case ("EDUCATION"):
                        int companySize = dis.readInt();
                        for (int k = 0; k < companySize; k++) { // цикл по Map<Link,List<Company>> companyMap
                            String name = dis.readUTF();
                            String website = dis.readUTF();
                            int companyListSize = dis.readInt();
                            for (int l = 0; l < companyListSize; l++) { // цикл по List<Company>
                                int periodListSize = dis.readInt();
                                for (int p = 0; p < periodListSize; p++) { // цикл по List<Period> periods
                                    LocalDate begin = readLocalDate(dis);
                                    LocalDate end = readLocalDate(dis);
                                    String tittle = dis.readUTF();
                                    String description = dis.readUTF();
                                    resume.addCompSectionData(st, name, website, begin, end, tittle, description);
                                }
                            }
                        }
                        break;
                }
            }


            return resume;
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private interface CollectionReader {
        void read() throws IOException;
    }

    private interface CollectionWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, CollectionWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void readCollection(DataInputStream dis, CollectionReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
