import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

class Bus {
    private int busNumber;
    private Stop[] stops;

    public Bus(int busNumber) {
        this.busNumber = busNumber;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public Stop[] getStops() {
        return stops;
    }

    public void setStops(Stop[] stops) {
        this.stops = stops;
    }
}

class Stop {
    public Bus sBus;
    public int sNumber;
    public ArrayList<Stop> adjStops;
    public Stop prevStop;
    public int minWayLength;
    public boolean isVisited;

    public Stop(){
        this.prevStop = null;
        this.minWayLength = Integer.MAX_VALUE;
        this.adjStops = new ArrayList<Stop>();
        this.isVisited = false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new InputStreamReader(new FileInputStream("in.txt")));
        FileWriter writer = new FileWriter("out.txt", false);

        int stopCount = scan.nextInt();
        int routeCount = scan.nextInt();
        int finalStop = scan.nextInt();
        int startStop = scan.nextInt();

        if(finalStop == startStop) {
            writer.write("0");
            writer.flush();
            return;
        }


        Bus[] buses = new Bus[routeCount];
        for(int i = 0 ; i < routeCount ; i++) {
            buses[i] = new Bus(i+1);
            Stop[] stops = new Stop[scan.nextInt()];
            for (int g = 0 ; g < stops.length ; g++){
                stops[g] = new Stop();
                stops[g].sBus = buses[i];
                stops[g].sNumber = scan.nextInt();
            }
            buses[i].setStops(stops);
        }
        findAdjacentStops(buses);

        Stop answerStop = findMinWay(startStop,finalStop,buses);
        if(answerStop == null) {
            writer.write("NoWay");
            writer.flush();
            return;
        }
        writer.write(Integer.toString(answerStop.minWayLength));
        ArrayList<String> answerTravel = new ArrayList<String>();
        while (answerStop != null) {
            if(answerStop.prevStop != null && answerStop.prevStop.sNumber != answerStop.sNumber) {
                answerTravel.add("StopNo " + answerStop.sNumber + " BusNo " + answerStop.sBus.getBusNumber());
            } else {
                answerTravel.add("StopNo " + answerStop.sNumber + " BusNo " + answerStop.sBus.getBusNumber());
                answerStop = answerStop.prevStop;
                if(answerStop == null) break;
            }
            if(answerStop.prevStop == null) {
                answerTravel.add("StopNo " + answerStop.sNumber + " BusNo " + answerStop.sBus.getBusNumber());
            }
            answerStop = answerStop.prevStop;
        }
        for(int i = answerTravel.size() - 1 ; i >= 0 ; i--) {
            writer.write("\n"+answerTravel.get(i));
        }

        writer.flush();
    }

    public static void findAdjacentStops(Bus[] buses){
        for(int i = 0 ; i < buses.length ; i++) {
            for (int g = 0 ; g < buses[i].getStops().length ; g++) {
                Stop currentStop = buses[i].getStops()[g];
                if(g == 0) {
                    Stop nextStop = buses[i].getStops()[g+1];
                    currentStop.adjStops.add(nextStop);
                }
                if(g != 0) {
                    Stop prevStop = buses[i].getStops()[g-1];
                    currentStop.adjStops.add(prevStop);
                    if(g < buses[i].getStops().length - 1) {
                        Stop nextStop = buses[i].getStops()[g+1];
                        currentStop.adjStops.add(nextStop);
                    }
                }
                findAdjacentStopsInOtherBuses(buses,currentStop,i);
                for (int k = 0 ; k < buses[i].getStops().length ; k++) {
                    Stop anotherStop = buses[i].getStops()[k];
                    if(Math.abs(g-k) > 3 && currentStop.sNumber == anotherStop.sNumber) {
                        currentStop.adjStops.add(anotherStop);
                    }
                }
            }
        }
    }

    public static void findAdjacentStopsInOtherBuses(Bus[] buses,Stop currentStop,int currentIndex) {
        for(int i = 0 ; i < buses.length ; i++) {
            if(i != currentIndex) {
                for (int g = 0; g < buses[i].getStops().length; g++) {
                    Stop anotherStop = buses[i].getStops()[g];
                    if(currentStop.sNumber == anotherStop.sNumber) {
                        currentStop.adjStops.add(anotherStop);
                    }
                }
            }
        }
    }




    public static Stop findMinWay(int startStopNumber, int finalStopNumber, Bus[] buses) {
        Stop finalStop = new Stop();
        if(startStopNumber == finalStopNumber) {
            finalStop.minWayLength = 0;
            return finalStop;
        }

        ArrayList<Stop> startStops = new ArrayList<Stop>();

        for (int i = 0 ; i < buses.length ; i++) {
            for(int g = 0 ; g < buses[i].getStops().length ; g++) {
                Stop currentStop = buses[i].getStops()[g];
                if(currentStop.sNumber == startStopNumber) {
                    currentStop.minWayLength = 0;
                    currentStop.prevStop = null;
                    currentStop.isVisited = true;
                    startStops.add(currentStop);
                }
            }
        }

        for(int i = 0 ; i < startStops.size() ; i++){
            Stop currentStartStop = startStops.get(i);
            ArrayList<Stop> adjUnvisitedStops = new ArrayList<Stop>();
            for(int g = 0 ; g < startStops.get(i).adjStops.size() ; g++) {
                Stop anotherStop = currentStartStop.adjStops.get(g);
                putLabels(currentStartStop,anotherStop);
                if(currentStartStop.minWayLength < anotherStop.minWayLength) {
                    adjUnvisitedStops.add(anotherStop);
                }
            }
            while(adjUnvisitedStops.size() != 0) {
                ArrayList<Stop> adjAdjUnvisitedStops = new ArrayList<Stop>();
                for(int g = 0 ; g < adjUnvisitedStops.size() ; g++) {
                    Stop currentStop = adjUnvisitedStops.get(g);
                    for(int k = 0 ; k < currentStop.adjStops.size() ; k++) {
                        Stop anotherStop = currentStop.adjStops.get(k);
                        if(currentStop.minWayLength < anotherStop.minWayLength) {
                            putLabels(currentStop,anotherStop);
                            adjAdjUnvisitedStops.add(anotherStop);
                        }
                    }
                }
                adjUnvisitedStops = adjAdjUnvisitedStops;
            }
        }


        boolean answerIndex = false;
        for (int i = 0 ; i < buses.length ; i++) {
            for(int g = 0 ; g < buses[i].getStops().length ; g++) {
                Stop currentStop = buses[i].getStops()[g];
                if(currentStop.sNumber == finalStopNumber) {
                   if (finalStop.minWayLength > currentStop.minWayLength) {
                       finalStop = currentStop;
                       answerIndex = true;
                   }
                }
            }
        }
        if(answerIndex) {
            return finalStop;
        }
        return null;
    }

    private static void putLabels(Stop currentStop, Stop anotherStop) {
        if(currentStop.sBus.getBusNumber() == anotherStop.sBus.getBusNumber()) {
            if(currentStop.sNumber == anotherStop.sNumber && currentStop.minWayLength + 3 < anotherStop.minWayLength) {
                anotherStop.minWayLength = currentStop.minWayLength + 3;
                anotherStop.prevStop = currentStop;
            } else if(currentStop.minWayLength + 1 < anotherStop.minWayLength) {
                anotherStop.minWayLength = currentStop.minWayLength + 1;
                anotherStop.prevStop = currentStop;
            }
        } else {
            if(currentStop.minWayLength + 3 < anotherStop.minWayLength) {
                anotherStop.minWayLength = currentStop.minWayLength + 3;
                anotherStop.prevStop = currentStop;
            }
        }
    }
}