package com.urise.popovas.webapp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Period {
    private LocalDate begin;
    private LocalDate end;
    private String tittle;
    private String description;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(begin.getMonthValue() +"/" + begin.getYear() + " - " + end.getMonthValue() +"/" + end.getYear() + "\n");
        if (tittle != null && tittle != "") {
            sb.append(tittle + "\n");
        }
        sb.append(description + "\n");
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

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Period() {
    }

    public Period(LocalDate begin, LocalDate end, String description) {
        this.begin = begin;
        this.end = end;
        this.description = description;
    }

    public Period(LocalDate begin, LocalDate end, String tittle, String description) {
        this.begin = begin;
        this.end = end;
        this.tittle = tittle;
        this.description = description;
    }
}
