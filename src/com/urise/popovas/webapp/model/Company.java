package com.urise.popovas.webapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company {
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
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(website, company.website) &&
                Objects.equals(periods, company.periods);
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
