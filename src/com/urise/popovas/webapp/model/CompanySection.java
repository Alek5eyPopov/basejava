package com.urise.popovas.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private final List<Company> companyList = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Company comp : companyList) {
            sb.append(comp + "\n");
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

    public CompanySection() {
    }
}
