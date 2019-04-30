package com.termilion.realmartisan.model;

import java.util.*;

public class DiceRoller extends Random {

    /**
     * Default die-size for the roller
     */
    private int dieSize;

    /**
     * @param size sets default die-size
     */
    public DiceRoller(int size) {
        this.dieSize = size;
    }

    /**
     * @return a random number rolled with a default-sized die
     */
    public int roll() {
        return nextInt(dieSize)+1;
    }

    /**
     * @param size custom die-size for this roll
     * @return a random number rolled with a custom-sized die
     */
    public int roll(int size) {
        return nextInt(size)+1;
    }

    /**
     * @param size custom die-size for these rolls
     * @param number number of rolls
     * @return array of rolled numbers made with a custom-sized die
     */
    public int[] roll(int size, int number) {
        int[] rolledValues = new int[number];
        for(int i = 0; i < number; i++) {
            rolledValues[i] = roll(size);
        }
        return rolledValues;
    }

    /**
     * @param array origin array
     * @param <T> type of array
     * @return random element from the array
     */
    public <T> T randomElement(T[] array) {
        return array[nextInt(array.length)];
    }

    /**
     * @param list origin list
     * @param <T> type of list
     * @return random element from the list
     */
    public <T> T randomElement(List<T> list) {
        return list.get(nextInt(list.size()));
    }

    public <T> Set<T> randomSubset(T[] array, int size){
        List<T> linkedList = new LinkedList<T>(Arrays.asList(array));
        Collections.shuffle(linkedList);
        return new HashSet<T>(linkedList.subList(0,size));
    }

    public <T> Set<T> randomSubset(List<T> list, int size){
        List<T> linkedList = new LinkedList<T>(list);
        Collections.shuffle(linkedList);
        return new HashSet<T>(linkedList.subList(0,size));
    }
}
