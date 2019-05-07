package com.termilion.realmartisan.model;

import com.google.common.collect.ImmutableRangeMap;
import com.google.common.collect.Range;

import javax.json.*;
import java.io.StringReader;
import java.util.List;


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
        this.distMap = fromJson(distXML);
        this.maxValue = distMap.asDescendingMapOfRanges()
                .keySet().asList().get(0).upperEndpoint();
    }

    public Distribution(List<String> distList) {
        ImmutableRangeMap.Builder<Integer, String> builder =
                new ImmutableRangeMap.Builder<>();
        int[] lowerBound = new int[]{1};
        distList.forEach(value -> {
            int upperBound = lowerBound[0] + 10;
            builder.put(Range.closed(lowerBound[0], upperBound), value);
            lowerBound[0] = upperBound+1;
        });
        this.distMap = builder.build();
        this.maxValue = lowerBound[0]+1;
    }

    // Methods

    public String roll(){
        return roll(new DiceRoller(20));
    }

    public String roll(DiceRoller roller){
        return distMap.get(roller.roll(maxValue));
    }


    private ImmutableRangeMap<Integer, String> fromJson(String distJson) {
        ImmutableRangeMap.Builder<Integer, String> builder = new ImmutableRangeMap.Builder<Integer, String>();
        JsonReader reader = Json.createReader(new StringReader(distJson));
        JsonObject json = reader.readObject();
        JsonArray dist = json.getJsonArray("distribution");
        final int[] nextLowerBound = new int[]{1};
        dist.forEach(element -> {
            JsonObject object  = element.asJsonObject();
            String key = object.getString("key");
            int range = Integer.parseInt(object.get("range").toString());
            int nextUpperBound = nextLowerBound[0] + range-1;
            builder.put(Range.closed(nextLowerBound[0],nextUpperBound), key);
            nextLowerBound[0] = nextUpperBound+1;
        });
        return builder.build();
    }

    public String toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        distMap.asMapOfRanges().forEach((rangeObject, key) -> {
            JsonObjectBuilder elementBuilder = Json.createObjectBuilder();
            int range = rangeObject.upperEndpoint()+1 - rangeObject.lowerEndpoint();
            elementBuilder.add("key", key);
            elementBuilder.add("range", range);
            arrayBuilder.add(elementBuilder.build());
        });
        builder.add("distribution", arrayBuilder.build());

        return builder.build().toString();
    }

    // Overrides

    @Override
    public String toString() {
        return toJson();
    }

    // Getters and Setters

    public int getMaxValue() {
        return maxValue;
    }

    public ImmutableRangeMap<Integer, String> getDistMap() {
        return distMap;
    }
}
