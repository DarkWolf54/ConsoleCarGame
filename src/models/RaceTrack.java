package models;


import java.util.List;

public class RaceTrack {

    private Double trackLength = 0.0d;
    private int numberOfLanes = 0;
    private List<Lanes> lanesArray;


    public Double getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(Double length) {
        this.trackLength = length;
    }

    public int getNumberOfLanes() {
        return numberOfLanes;
    }

    public void setNumberOfLanes(int numberOfLanes) {
        this.numberOfLanes = numberOfLanes;
    }

    public List<Lanes> getLanesArray() {
        return lanesArray;
    }

    public void setLanesArray(List<Lanes> lanesArray) {
        this.lanesArray = lanesArray;
    }
}
