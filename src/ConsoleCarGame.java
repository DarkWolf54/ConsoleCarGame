import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleCarGame {

    private static Scanner input = new Scanner(System.in);
    private static final double METERS = 100.0;
    private static final int WINNERS_ALLOWED = 3;
    private static final int MINIMUM_LANES = 3;
    private static final int KM_TO_METERS = 1000;

    public static void main (String [ ] args) {

        RaceTrack track = new RaceTrack();
        Podium podium = new Podium();

        while(true){


            System.out.println("Type the number of the option you want: ");
            System.out.println("1. Play!");
            System.out.println("2. Show winners history.");
            System.out.println("0. Exit game.");
            int option = Integer.valueOf(input.nextLine());

            if(option == 0){
                break;
            }

            if(option == 1){
                numLanes(track);
                if(track.getNumberOfLanes() < MINIMUM_LANES){
                    System.out.println("There has to be at least three lanes.");
                    continue;
                }
                else{
                    createRaceLenght(track);
                    kmToMeters(track);
                    playGame(createLanesList(track.getNumberOfLanes(), track),
                            track.getNumberOfLanes(), track.getTrackLength());
                }
            }

        }
    }

    static double createRaceLenght(RaceTrack track){

        System.out.println("Lenght of the race(in Km)?");
        double raceLenght = Double.valueOf(input.nextLine());
        track.setTrackLength(raceLenght);
        //System.out.println(track.lenght);
        return  track.getTrackLength();

    }

    static int numLanes(RaceTrack track){
        System.out.println("Number of lanes? Each lane has a car.");
        int nOfLanes = Integer.valueOf(input.nextLine());
        track.setNumberOfLanes(nOfLanes);
        //System.out.println(track.numberOfLanes);
        return track.getNumberOfLanes();

    }

    static Driver createDriver(String name){
        Driver driver = new Driver(name);
        return driver;
    }

    static Car createCars(Driver driver){
        Car car = new Car();
        car.setDriver(driver);
        return car;
    }

    static Lanes createLanes(Car car){
        Lanes lanes = new Lanes();
        lanes.setCar(car);
        return lanes;
    }

    static int throwDice(){
        Random randInt = new Random();
        int dice = randInt.nextInt(6-1) + 1;
        return dice;
    }

    static List<Lanes> createLanesList(int numberOfLanes, RaceTrack track){
        List<Lanes> lanesList = new ArrayList<>();
        for(int i = 0; i < numberOfLanes; i++){
            System.out.println("Enter " + (i+1) + " Driver's name:");
            String driverName = input.nextLine();
            lanesList.add(i, createLanes(createCars(createDriver(driverName))));
        }
        return  lanesList;

    }


    static  void announcment(Driver driver, int steps){
        System.out.println(driver.getName() + " threw the dice and got: " + steps +
                "." + "Advances: " + steps*METERS + " meters");
    }

    static void playGame(List<Lanes> lanesList, int numberOfLanes, double trackLenght){
        List<Driver> winners = new ArrayList<>();
        gameLoop(lanesList, trackLenght, winners);
        plusWins(winners);

    }

    private static void gameLoop(List<Lanes> lanesList, double trackLenght, List<Driver> winners) {
        while (winners.size() < WINNERS_ALLOWED){
            for (int i = 0; i < lanesList.size(); i++) {
                double position = lanesList.get(i).getCar().getPosition();
                lanesList.get(i).getCar().setPosition(position + throwDice() * METERS);
                fillPodium(lanesList, trackLenght, winners, i);
            }
        }
    }

    private static void fillPodium(List<Lanes> lanesList, double trackLenght, List<Driver> winners, int i) {
        if (lanesList.get(i).getCar().getPosition() >= trackLenght
        && winners.size() < WINNERS_ALLOWED){
            winners.add(lanesList.get(i).getCar().getDriver());
            lanesList.remove(i);
        }
    }

    private static void plusWins(List<Driver> winners) {
        int wins = winners.get(0).getWins();
        winners.get(0).setWins(wins+1);
        showWinners(winners);
    }

    private static void showWinners(List<Driver> winners) {
        for(int i = 0; i < winners.size();i++){
            System.out.println((i+1) + ". " + winners.get(i).getName() + " Wins: "+ winners.get(i).getWins());

        }
    }

    static double kmToMeters(RaceTrack track){
        double km = track.getTrackLength();
        track.setTrackLength(km * KM_TO_METERS);
        return track.getTrackLength();
    }

}
