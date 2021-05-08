import java.util.Scanner;

public class ConsoleCarGame {

    static Scanner input = new Scanner(System.in);

    public static void main (String [ ] args) {

        RaceTrack track = new RaceTrack();

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
                createRaceLenght(track);
                numLanes(track);
                createLanes(track.numberOfLanes);
            }

        }
    }

    static void createRaceLenght(RaceTrack track){

        System.out.println("Lenght of the race(in KM)?");
        track.lenght = Integer.valueOf(input.nextLine());
        //System.out.println(track.lenght);

    }

    static int numLanes(RaceTrack track){
        System.out.println("Number of lanes? Each lane has a car.");
        track.numberOfLanes = Integer.valueOf(input.nextLine());
        //System.out.println(track.numberOfLanes);
        return track.numberOfLanes;
    }

    static void createLanes(int numberOfLanes){
        Lanes[] lanesArray;
        lanesArray = new Lanes[numberOfLanes];
        //System.out.println(lanesArray.length);
    }
}
