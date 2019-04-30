package com.termilion.realmartisan.model;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class Distribution {

    // Attributes

    ImmutableRangeMap<Integer, String> distMap;
    int maxValue;

    // Constructors

    public Distribution(ImmutableRangeMap<Integer, String> distMap){
        this.distMap = distMap;
        this.maxValue = distMap.asDescendingMapOfRanges()
                .keySet().asList().get(0).upperEndpoint();
    }

    public Distribution(String distXML){
        this.distMap = fromXML(distXML);
        this.maxValue = distMap.asDescendingMapOfRanges()
                .keySet().asList().get(0).upperEndpoint();
    }

    // Methods

    public String roll(){
        return roll(new DiceRoller(20));
    }

    public String roll(DiceRoller roller){
        return distMap.get(roller.roll(maxValue));
    }

    private ImmutableRangeMap<Integer, String> fromXML(String distXML) {
        final ImmutableRangeMap.Builder<Integer, String> builder = new ImmutableRangeMap.Builder<Integer, String>();
        try {
            InputStream stream = new ByteArrayInputStream(distXML.getBytes(StandardCharsets.UTF_8));
            Document doc = new SAXBuilder().build(stream);
            Element root = doc.getRootElement();
            final int[] nextLowerBound = new int[]{1};
            root.getChildren().forEach(node -> {
              String key = node.getAttribute("key").getValue();
              int range = Integer.parseInt(node.getAttribute("range").getValue());
              int nextUpperBound = nextLowerBound[0] + range-1;
              builder.put(Range.closed(nextLowerBound[0],nextUpperBound), key);
              nextLowerBound[0] = nextUpperBound+1;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.build();
    }

    public String toXML() {
        Element root = new Element("distribution");
        distMap.asMapOfRanges().forEach((rangeObject, key) -> {
            int range = rangeObject.upperEndpoint()+1 - rangeObject.lowerEndpoint();
            Element element = new Element("element");
            element.setAttribute("key", key);
            element.setAttribute("range", String.format("%s", range));
            root.addContent(element);
        });
        return new XMLOutputter().outputString(new Document(root));
    }

    // Overrides

    @Override
    public String toString() {
        return toXML();
    }

    // Getters and Setters

    public int getMaxValue() {
        return maxValue;
    }

    public ImmutableRangeMap<Integer, String> getDistMap() {
        return distMap;
    }
}
