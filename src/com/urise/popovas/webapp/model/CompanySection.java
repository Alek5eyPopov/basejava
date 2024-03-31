package com.urise.popovas.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompanySection extends Section {
    private final static long serialVersionUID = 1L;
    private final List<Company> companyList = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (Company comp : companyList) {
            sb.append(comp.getHomePage().getName());
            sb.append("\n");
            String url = comp.getHomePage().getUrl();
            if (url != null && !url.equals("")) {
                sb.append(url);
                sb.append("\n");
            }
            sb.append(comp);
            sb.append("\n");
        }

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

    CompanySection() {
    }
}
