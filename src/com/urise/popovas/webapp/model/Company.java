package com.urise.popovas.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    private final static long serialVersionUID = 1L;
    private Link homePage;
    private List<Period> periods;

    Company(String name, String website, List<Period> periods) {
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

    public Company() {
    }

    public void setHomePage(Link homePage) {
        this.homePage = homePage;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Period per : periods) {
            sb.append(per.toString());
            sb.append("\n");
        }
        return sb.toString();
    }


}
