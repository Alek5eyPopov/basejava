package com.urise.popovas.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection extends Section {
    private final static long serialVersionUID = 1L;
    private final List<Company> companyList = new ArrayList<>();
    private final Map<Link,List<Company>> companyMap = new HashMap<>();

    public Map<Link, List<Company>> getCompanyMap() {
        return companyMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        companyMap.forEach((key, value) -> {
            sb.append(key.getName() + "\n");
            if( key.getUrl() != null && key.getUrl() != "") {
                sb.append(key.getUrl() + "\n");
            }
            for (Company comp : value) {
                sb.append(comp + "\n");
            }
        });
        return sb.toString();
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
