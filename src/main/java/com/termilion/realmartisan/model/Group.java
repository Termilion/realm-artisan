package com.termilion.realmartisan.model;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "skinTones", nullable = false, length = 1000)
    private String skinTones;

    @Column(name = "hairTones", nullable = false, length = 1000)
    private String hairTones;

    @Column(name = "eyeTones", nullable = false, length = 1000)
    private String eyeTones;

    @Column(name = "age", nullable = false, length = 1000)
    private String age;

    @Column(name = "size", nullable = false, length = 1000)
    private String size;

    @Column(name = "features", nullable = false)
    private String[] features;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

// -------------------- methods --------------------

    public Group() {}

    private Group(String name,
                  String skinTones,
                  String hairTones,
                  String eyeTones,
                  String age,
                  String size,
                  String[] features,
                  String description) {
        this.name = name;
        this.skinTones = skinTones;
        this.hairTones = hairTones;
        this.eyeTones = eyeTones;
        this.age = age;
        this.size = size;
        this.features = features;
        this.description = description;
    }

    private Group(String name,
                  Distribution skinTones,
                  Distribution hairTones,
                  Distribution eyeTones,
                  Distribution sex,
                  Distribution age,
                  Distribution size,
                  String[] features,
                  String description) {
        this.name = name;
        this.skinTones = skinTones.toString();
        this.hairTones = hairTones.toString();
        this.eyeTones = eyeTones.toString();
        this.age = age.toString();
        this.size = size.toString();
        this.features = features;
        this.description = description;
    }

    public Character rollCharacter(String name, String profession, Region region) {
        return rollCharacter(name, profession, region, new DiceRoller(20));
    }

    public Character rollCharacter(String name, String profession, Region region, DiceRoller roller) {
        int numFeat = (roller.roll(this.features.length)/2)+1;
        String skinTone = rollDistribution(skinTones, roller);
        String hairTone = rollDistribution(hairTones, roller);
        String eyeTone = rollDistribution(eyeTones, roller);
        String charAge = rollDistribution(age, roller);
        String charSize = rollDistribution(size, roller);
        String[] charFeatures = roller.randomSubset(features, numFeat).toArray(new String[0]);

        return new Character(name, skinTone, hairTone, eyeTone, charSize, charAge, profession, charFeatures, region, this);
    }

    public String rollDistribution(String xml){
        return new Distribution(xml).roll();
    }

    public String rollDistribution(String xml, DiceRoller roller){
        return new Distribution(xml).roll(roller);
    }

    @Override
    public String toString() {
        return String.format("%s", this.id);
    }

    // -------------------- getters and setters --------------------

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkinTones() {
        return skinTones;
    }

    public void setSkinTones(String skinTones) {
        this.skinTones = skinTones;
    }

    public String getHairTones() {
        return hairTones;
    }

    public void setHairTones(String hairTones) {
        this.hairTones = hairTones;
    }

    public String getEyeTones() {
        return eyeTones;
    }

    public void setEyeTones(String eyeTones) {
        this.eyeTones = eyeTones;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String[] getFeatures() {
        return features;
    }

    public void setFeatures(String[] features) {
        this.features = features;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAll(Group groupDetails) {
        setFeatures(groupDetails.getFeatures());
        setDescription(groupDetails.getDescription());
        setEyeTones(groupDetails.getEyeTones());
        setHairTones(groupDetails.getHairTones());
        setSkinTones(groupDetails.getSkinTones());
        setSize(groupDetails.getSize());
        setAge(groupDetails.getAge());
        setName(groupDetails.getName());
    }
}

