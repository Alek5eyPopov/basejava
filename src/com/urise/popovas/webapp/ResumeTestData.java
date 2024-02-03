package com.urise.popovas.webapp;

import com.urise.popovas.webapp.model.ContactType;
import com.urise.popovas.webapp.model.Resume;
import com.urise.popovas.webapp.model.SectionType;

import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {

        Resume resume = new Resume("Григорий Кислин");

        resume.addContact(ContactType.TELEPHONE, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        resume.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.addContact(ContactType.GITHUB, "https://github.com/gkislin");
        resume.addContact(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.addContact(ContactType.HOMEPAGE, "http://gkislin.ru/");

        resume.addTextSectionData(SectionType.OBJECTIVE, "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        resume.addTextSectionData(SectionType.PERSONAL, "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        resume.addTextSectionData(SectionType.ACHIEVEMENT, "Организация команды и успешная реализация Java проектов" +
                " для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей" +
                " спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        resume.addTextSectionData(SectionType.ACHIEVEMENT, "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven." +
                " Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\"." +
                " Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        resume.addTextSectionData(SectionType.ACHIEVEMENT, "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с" +
                " Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        resume.addTextSectionData(SectionType.ACHIEVEMENT, "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita" +
                " BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка" +
                " SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        resume.addTextSectionData(SectionType.ACHIEVEMENT, "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT," +
                " ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга." );
        resume.addTextSectionData(SectionType.ACHIEVEMENT, "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base" +
                " архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему" +
                " мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/Django).");
        resume.addTextSectionData(SectionType.ACHIEVEMENT, "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport," +
                " Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа." );

        resume.addTextSectionData(SectionType.QUALIFICATIONS, "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds," +
                " Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons," +
                " Eclipse SWT, JUnit, Selenium (htmlelements).");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Python: Django.");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB," +
                " JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных" +
                " шаблонов, UML, функционального программирования");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis," +
                " Bonita, pgBouncer");
        resume.addTextSectionData(SectionType.QUALIFICATIONS, "Родной русский, английский \"upper intermediate\"" );

        List<String> expList = new ArrayList<>();

        expList.add("Создание, организация и проведение Java онлайн проектов и стажировок.");
        resume.addExpSectionData(SectionType.EXPERIENCE, "10/2013 - Сейчас", "Java Online Projects", "Автор проекта.", expList);
        expList = new ArrayList<>();

        expList.add("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). ");
        expList.add("Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        resume.addExpSectionData(SectionType.EXPERIENCE, "10/2014 - 01/2016", "Wrike", "Старший разработчик (backend)", expList);
        expList = new ArrayList<>();

        expList.add("Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO.");
        expList.add("Архитектура БД и серверной части системы.");
        expList.add("Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). ");
        expList.add("Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office.");
        expList.add("Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        resume.addExpSectionData(SectionType.EXPERIENCE, "04/2012 - 10/2014", "RIT Center", "Java архитектор", expList);
        expList = new ArrayList<>();

        expList.add("Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle).");
        expList.add("Реализация клиентской и серверной части CRM.");
        expList.add("Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга.");
        expList.add("JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        resume.addExpSectionData(SectionType.EXPERIENCE, "12/2010 - 04/2012", "Luxoft (Deutsche Bank)", "Ведущий программист", expList);
        expList = new ArrayList<>();

        expList.add("Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2).");
        expList.add("Реализация администрирования, статистики и мониторинга фреймворка.");
        expList.add("Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        resume.addExpSectionData(SectionType.EXPERIENCE, "06/2008 - 12/2010", "Yota", "Ведущий специалист", expList);
        expList = new ArrayList<>();

        expList.add("Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        resume.addExpSectionData(SectionType.EXPERIENCE, "03/2007 - 06/2008", "Enkata", "Разработчик ПО", expList);
        expList = new ArrayList<>();

        expList.add("Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        resume.addExpSectionData(SectionType.EXPERIENCE, "01/2005 - 02/2007", "Siemens AG", "Разработчик ПО", expList);
        expList = new ArrayList<>();

        expList.add("Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        resume.addExpSectionData(SectionType.EXPERIENCE, "09/1997 - 01/2005", "Alcatel", "Инженер по аппаратному и программному тестированию", expList);

        resume.addExpSectionData(SectionType.EDUCATION, "03/2013 - 05/2013", "Coursera", "'Functional Programming Principles in Scala' by Martin Odersky", null);
        resume.addExpSectionData(SectionType.EDUCATION, "03/2011 - 04/2011", "Luxoft", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", null);
        resume.addExpSectionData(SectionType.EDUCATION, "01/2005 - 04/2005", "Siemens AG", "3 месяца обучения мобильным IN сетям (Берлин)", null);
        resume.addExpSectionData(SectionType.EDUCATION, "09/1997 - 03/1998", "Alcatel", "6 месяцев обучения цифровым телефонным сетям (Москва)", null);
        resume.addExpSectionData(SectionType.EDUCATION, "09/1993 - 07/1996", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "Аспирантура (программист С, С++)", null);
        resume.addExpSectionData(SectionType.EDUCATION, "09/1987 - 07/1993", "Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                "Инженер (программист Fortran, C)", null);
        resume.addExpSectionData(SectionType.EDUCATION, "09/1984 - 06/1987", "Заочная физико-техническая школа при МФТИ", "Закончил с отличием", null);

        System.out.println(resume);
    }
}
