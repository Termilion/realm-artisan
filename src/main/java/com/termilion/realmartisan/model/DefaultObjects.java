/*package com.termilion.realmartisan.model;

import java.util.Map;

public class DefaultObjects {

// --------------------- default human ---------------------

    static Map<String, Integer> human_age = Map.of("child", 5, "young", 10, "teen", 20, "adult", 40, "mature", 20, "old", 5 );
    static Map<String, Integer> human_sex = Map.of("male", 45, "female", 45, "other", 10);
    static Map<String, Integer> human_size = Map.of("150", 5, "160", 15, "170", 25, "180", 35, "190", 15, "200", 5);
    static Map<String, Integer> human_skinTone = Map.of("snow white", 5, "pale", 25, "tanned", 40, "dark", 25, "black", 5);
    static Map<String, Integer> human_hairTone = Map.of("white", 5, "blonde", 30, "brown", 30, "red", 5, "black", 30);
    static Map<String, Integer> human_eyeTone = Map.of("grey", 10, "blue", 25, "brown", 35, "green", 25, "lavender", 5);
    static String[] human_features = new String[] {"curly hair", "muscular", "overweight", "charismatic", "rich", "pierced", "tattooed", "intelligent", "bearded"};
    static String human_description = "A normal human";

    public Ethnicity DEFAULT_HUMAN = new Ethnicity();
            DEFAULT_HUMAN.setName("Human")
            .withAge(new Distribution<String>(human_age))
            .withDescription(human_description)
            .withEyeTones(new Distribution<>(human_eyeTone))
            .withHairTones(new Distribution<>(human_hairTone))
            .withSkinTones(new Distribution<>(human_skinTone))
            .withFeatures(human_features)
            .withSize(new Distribution<>(human_size))
            .withSex(new Distribution<>(human_sex))
            .build();

// --------------------- default elf ---------------------

    static Map<String, Integer> elf_age = Map.of("child", 10, "young", 15, "teen", 20, "adult", 40, "mature", 14, "old", 1 );
    static Map<String, Integer> elf_sex = Map.of("male", 40, "female", 40, "other", 20);
    static Map<String, Integer> elf_size = Map.of("160", 10, "170", 25, "180", 35, "190", 25, "200", 5);
    static Map<String, Integer> elf_skinTone = Map.of("snow white", 15, "pale", 40, "tanned", 30, "dark", 10, "black", 5);
    static Map<String, Integer> elf_hairTone = Map.of("white", 10, "blonde", 30, "brown", 30, "black", 30);
    static Map<String, Integer> elf_eyeTone = Map.of("fiery",  10, "grey", 10, "blue", 25, "brown", 5, "green", 35, "lavender", 15);
    static String[] elf_features = new String[] {"charismatic", "long hair", "pierced", "tattoed", "bearded", "athletic", "glassed", "intelligent", "muscular", "braided hair"};
    static String elf_description = "A normal elf";

    public static final Ethnicity DEFAULT_ELF = Ethnicity.newBuilder()
            .withCommonName("Elf")
            .withAge(new Distribution<String>(elf_age))
            .withDescription(elf_description)
            .withEyeTones(new Distribution<>(elf_eyeTone))
            .withHairTones(new Distribution<>(elf_hairTone))
            .withSkinTones(new Distribution<>(elf_skinTone))
            .withFeatures(elf_features)
            .withSize(new Distribution<>(elf_size))
            .withSex(new Distribution<>(elf_sex))
            .build();

// --------------------- default elf ---------------------

    static Map<String, Integer> dwarf_age = Map.of("child", 10, "young", 15, "teen", 20, "adult", 40, "mature", 14, "old", 1 );
    static Map<String, Integer> dwarf_sex = Map.of("male", 40, "female", 40, "other", 20);
    static Map<String, Integer> dwarf_size = Map.of("160", 10, "170", 25, "180", 35, "190", 25, "200", 5);
    static Map<String, Integer> dwarf_skinTone = Map.of("snow white", 15, "pale", 40, "tanned", 30, "dark", 10, "black", 5);
    static Map<String, Integer> dwarf_hairTone = Map.of("white", 10, "blonde", 30, "brown", 30, "black", 30);
    static Map<String, Integer> dwarf_eyeTone = Map.of("fiery",  10, "grey", 10, "blue", 25, "brown", 5, "green", 35, "lavender", 15);
    static String[] dwarf_features = new String[] {"charismatic", "long hair", "pierced", "tattoed", "bearded", "athletic", "glassed", "intelligent", "muscular", "braided hair"};
    static String dwarf_description = "A normal elf";

    public static final Ethnicity DEFAULT_DWARF = Ethnicity.newBuilder()
            .withCommonName("Dwarf")
            .withAge(new Distribution<String>(dwarf_age))
            .withDescription(dwarf_description)
            .withEyeTones(new Distribution<>(dwarf_eyeTone))
            .withHairTones(new Distribution<>(dwarf_hairTone))
            .withSkinTones(new Distribution<>(dwarf_skinTone))
            .withFeatures(dwarf_features)
            .withSize(new Distribution<>(dwarf_size))
            .withSex(new Distribution<>(dwarf_sex))
            .build();


// --------------------- default region ---------------------

    static Map<Ethnicity, Integer> ethn_map = Map.of(DEFAULT_HUMAN, 50, DEFAULT_DWARF, 25, DEFAULT_ELF, 25);
    static String[] professions = new String[] {"Smith", "Hunter", "Fisherman", "Artist", "Author", "Composer",
            "Poet", "Writer", "Hawker", "Rat Catcher", "Armorer", "Saddler", "Bladesmith", "Fletcher", "Courier",
            "Blacksmith", "Shoemaker", "Falconer", "Jeweler", "Barkeep", "Noble", "Knight", "Guardian",
            "Sellsword", "Cutpurse", "Fence", "Bard", "Scholar", "Prostitute", "Citywatch", "Priest",
            "Apothecary", "Librarian", "Scribe", "Tutor", "Herbalist", "Philosopher", "Sailor", "Navigator",
            "Priest", "Lawyer", "Barber", "Beggar", "Waiter", "Vagabond", "Housewife/-man", "Doctor",
            "Cook", "Pilgrim", "Executioner", "Miller", "Guide", "Fortune Teller", "Gardener",
            "Spy", "Shopkeeper", "Trader", "Arcanist"};
    static String commonName = "Alessa";

    public static final Region DEFAULT_REGION = Region.newBuilder()
            .withCommonName(commonName)
            .withEthnicities(new Distribution<Ethnicity>(ethn_map))
            .withProfessions(new Distribution<String>(professions))
            .build();

}
*/