package com.urise.popovas.webapp.util;

import com.urise.popovas.webapp.model.Company;
import com.urise.popovas.webapp.model.Link;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.List;

class MapElements {
    @XmlAttribute
    public Link key;
    @XmlAttribute
    public List<Company> value;

    private MapElements() {
    } //Required by JAXB

    public MapElements(Link key, List<Company> value) {
        this.key = key;
        this.value = value;
    }
}
