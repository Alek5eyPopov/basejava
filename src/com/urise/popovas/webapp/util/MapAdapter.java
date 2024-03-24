package com.urise.popovas.webapp.util;

import com.urise.popovas.webapp.model.Company;
import com.urise.popovas.webapp.model.Link;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;
import java.util.Map;

public class MapAdapter extends XmlAdapter<MapElements[], Map<Link, List<Company>>> {
    @Override
    public Map<Link, List<Company>> unmarshal(MapElements[] v) throws Exception {
        return null;
    }

    @Override
    public MapElements[] marshal(Map<Link, List<Company>> v) throws Exception {
        MapElements[] mapElements = new MapElements[v.size()];
//        int i = 0;
//        for (Map.Entry<String, String> entry : v.entrySet())
//            mapElements[i++] = new MapElements(entry.getKey(), entry.getValue());
//
        return mapElements;
    }
}
