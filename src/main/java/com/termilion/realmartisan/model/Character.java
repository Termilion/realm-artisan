package com.termilion.realmartisan.model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "characters")
public class Character {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "skin", nullable = false)
    private String skin;

    @Column(name = "hair", nullable = false)
    private String hair;

    @Column(name = "eyes", nullable = false)
    private String eyes;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "age", nullable = false)
    private String age;

    @Column(name = "features", nullable = false)
    private String[] features;

    @Column(name = "profession", nullable = false)
    private String profession;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private Region region;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", nullable = true)
    private Ethnicity ethnicity;

    // Constructors

    public Character() {
    }

    public Character(String name,
                     String skin, String hair,
                     String eyes, String size,
                     String age, String profession,
                     String[] features, Region region,
                     Ethnicity ethnicity) {
        this.name = name;
        this.skin = skin;
        this.hair = hair;
        this.eyes = eyes;
        this.size = size;
        this.age = age;
        this.profession = profession;
        this.features = features;
        this.region = region;
        this.ethnicity = ethnicity;
    }

    // Methods

    // Overrides

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", skin='" + skin + '\'' +
                ", hair='" + hair + '\'' +
                ", eyes='" + eyes + '\'' +
                ", size='" + size + '\'' +
                ", age=" + age + '\'' +
                ", region=" + region + '\'' +
                ", ethnicity=" + ethnicity + '\'' +
                ", profession=" + profession + '\'' +
                ", features=" + Arrays.toString(features) +
                '}';
    }

    // Getters / Setters

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getHair() {
        return hair;
    }

    public void setHair(String hair) {
        this.hair = hair;
    }

    public String getEyes() {
        return eyes;
    }

    public void setEyes(String eyes) {
        this.eyes = eyes;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(Ethnicity ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setAll(Character charDetails){
        this.setName(charDetails.getName());
        this.setAge(charDetails.getAge());
        this.setSize(charDetails.getSize());
        this.setEyes(charDetails.getEyes());
        this.setHair(charDetails.getHair());
        this.setSkin(charDetails.getSkin());
        this.setProfession(charDetails.getProfession());
        this.setFeatures(charDetails.getFeatures());
        this.setRegion(charDetails.getRegion());
        this.setEthnicity(charDetails.getEthnicity());
    }
}
