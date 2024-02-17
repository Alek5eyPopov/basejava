package com.urise.popovas.webapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Company implements Serializable {

    private Link homePage;
    private List<Period> periods = new ArrayList<>();

    public Company(String name, String website, List<Period> periods) {
        this.homePage = new Link(name,website);
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(homePage, company.homePage) &&
                Objects.equals(periods, company.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    public Link getHomePage() {
        return homePage;
    }

    public void setHomePage(Link homePage) {
        this.homePage = homePage;
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
        this.homePage = new Link(name,website);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Period per : periods) {
            sb.append(per.toString() + "\n");
        }
        return sb.toString();
    }


}
