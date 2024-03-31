package com.urise.popovas.webapp.model;

import com.urise.popovas.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Period implements Serializable {
    private final static long serialVersionUID = 1L;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate begin;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate end;
    private String tittle;
    private String description;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(begin.getMonthValue() +"/" + begin.getYear() + " - " + end.getMonthValue() +"/" + end.getYear());
        sb.append("\n");
        if (tittle != null && !tittle.equals("")) {
            sb.append(tittle);
            sb.append("\n");
        }
        sb.append(description);
        sb.append("\n");
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

    public Period() {
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getTittle() {
        return tittle;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    Period(LocalDate begin, LocalDate end, String tittle, String description) {
        this.begin = begin;
        this.end = end;
        this.tittle = tittle;
        this.description = description;
    }
}
