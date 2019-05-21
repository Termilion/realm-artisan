package com.termilion.realmartisan.model;

import com.termilion.realmartisan.exception.ResourceNotFoundException;

import javax.persistence.*;
import java.util.List;

@Entity
public class Region {

    // Attributes

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "ethnicities", nullable = false, length = 10000)
    private String ethnicities;

    @Column(name = "professions", nullable = false, length = 10000)
    private String professions;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Region superregion;

    @OneToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Region> subregions;

    // Constructors

    public Region() {
    }

    public Region(String name, String ethnicities, String professions,
                  Region superregion, List<Region> subregions) {
        this.name = name;
        this.ethnicities = ethnicities;
        this.professions = professions;
        this.superregion = superregion;
        this.subregions = subregions;
    }

    // Methods

    public Character rollCharacter(String name, Group group) throws ResourceNotFoundException {
        String profession = new Distribution(this.professions).roll();
        return group.rollCharacter(name, profession, this);
    }

    public void addSubregion(Region region){
        this.subregions.add(region);
    }

    public void removeSubregion(Region region){
        this.subregions.remove(region);
    }

    // Overrides

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name=" + name +
                ", ethnicities='" + ethnicities + '\'' +
                ", professions='" + professions + '\'' +
                '}';
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEthnicities() {
        return ethnicities;
    }

    public void setEthnicities(String ethnicities) {
        this.ethnicities = ethnicities;
    }

    public String getProfessions() {
        return professions;
    }

    public void setProfessions(String professions) {
        this.professions = professions;
    }

    public Region getSuperregion() {
        return superregion;
    }

    public void setSuperregion(Region superregion) {
        this.superregion = superregion;
    }

    public List<Region> getSubregions() {
        return subregions;
    }

    public void setSubregions(List<Region> subregions) {
        this.subregions = subregions;
    }

    public void setAll(Region regionDetails) {
        setName(regionDetails.getName());
        setEthnicities(regionDetails.getEthnicities());
        setProfessions(regionDetails.getProfessions());
        setSubregions(regionDetails.getSubregions());
        setSuperregion(regionDetails.getSuperregion());
    }
}
