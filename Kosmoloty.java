import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Kosmoloty{
    public static void main(String[] args) {

        Torus torus = new Torus(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        KosmolotyList kosmoloty = new KosmolotyList(torus, args);

        Collider collider = new Collider();

        int raceLength = 86400;

        for(int i = 0; i<raceLength; i++) {
            for (int j = 0; j < kosmoloty.list.size(); j++) {
                kosmoloty.list.get(j).changePosition(torus);
            }

            collider.forEachObjectCollision(kosmoloty);
        }

        System.out.println(findWinner(kosmoloty));

        System.exit(0);
    }

    static String findWinner(KosmolotyList kosmolotyList){
        int firstCandidateIndex = 0;

        //bierzemy pierwszy niezniszczony kosmolot
        while(!kosmolotyList.list.get(firstCandidateIndex).getIsAlive()){
            firstCandidateIndex ++;
            if(firstCandidateIndex == kosmolotyList.list.size()){
                System.out.print("remis");
                System.exit(0);
            }
        }

        Kosmolot winner = kosmolotyList.list.get(firstCandidateIndex);

        //szukamy komosloty o najwięszkej przebytej trasie
        for (int i = firstCandidateIndex+1; i < kosmolotyList.list.size(); i++) {
            if(kosmolotyList.list.get(i).getTraveledDistance() > winner.getTraveledDistance() && kosmolotyList.list.get(i).getIsAlive()){
                winner = kosmolotyList.list.get(i);
            }
        }

        //sprawdzamy, czy nie ma takiego kosmolotu, który przebył taką samą trasę co wybrany na tę chwilę zwycięsca
        for (int i = 0; i < kosmolotyList.list.size(); i++) {
            if(winner.getTraveledDistance() == kosmolotyList.list.get(i).getTraveledDistance() && kosmolotyList.list.get(i).getIsAlive() && kosmolotyList.list.get(i) != winner){
                System.out.print("remis");
                System.exit(0);
            }
        }

        return winner.getName();
    }
}

class Kosmolot {
    private String name;
    private int speedX, speedY;
    private int positionX, positionY;
    private int traveledDistance;
    private int traveledDistanceInOneMove;
    private boolean isAlive;

    Kosmolot(String name, int speedX, int speedY, int positionX, int positionY, Torus torus) {

        this.name = name;
        this.speedX = speedX;
        this.speedY = speedY;
        this.positionX = positionX;
        this.positionY = positionY;
        this.isAlive = true;

        traveledDistanceInOneMove = speedX + speedY;
        traveledDistance = 0;

        if (!checkDataCorectness(torus)) {
            System.out.print("klops");
            System.exit(0);
        }
    }

    public void changePosition(Torus torus) {
        positionX = positionX + speedX;
        positionY = positionY + speedY;

        traveledDistance += traveledDistanceInOneMove;

        // sprawdzamy czy przechodzi przez brzeg torusa, jeśli tak to przypisujemy odpowiednie warości do pozycji:
        if (positionX > torus.getTorusLenX() - 1) positionX = positionX - (torus.getTorusLenX());

        if (positionY > torus.getTorusLenY() - 1) positionY = positionY - (torus.getTorusLenY());

        if (positionX < 0) positionX = torus.getTorusLenX() + positionX;

        if (positionY < 0) positionY = torus.getTorusLenY() + positionY;
    }

    public void kill() {
        isAlive = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public double getTraveledDistance() {
        return traveledDistance;
    }

    private boolean checkDataCorectness(Torus torus) {
        boolean isCorrect = false;

        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9]{1,10}+$");

        Matcher matcher = namePattern.matcher(name);
        if(matcher.matches()){
            isCorrect = true;
        } else {
            return false;
        }

        if (speedX >= -10000 && speedX <= 10000 && speedY >= -10000 && speedY <= 10000) {
            isCorrect = true;
        } else {
            return false;
        }

        if (positionX >= 0 && positionX <= torus.getTorusLenX() - 1 && positionY >= 0 && positionY <= torus.getTorusLenY() - 1) {
            isCorrect = true;
        } else {
            return false;
        }

        return isCorrect;
    }
}

class Torus {
    private int torusLenX;
    private int torusLenY;

    public Torus(int torusLenX, int torusLenY) {

        this.torusLenX = torusLenX;
        this.torusLenY = torusLenY;

        if (!checkDataCorectness()) {
            System.out.print("klops");
            System.exit(0);
        }
    }

    public int getTorusLenX(){
        return torusLenX;
    }

    public int getTorusLenY(){
        return torusLenY;
    }

    public boolean checkDataCorectness() {
        if (torusLenX > 1 && torusLenX < 100000 && torusLenY > 1 && torusLenY < 100000) {
            return true;
        } else {
            return false;
        }
    }
}

class Collider {
    public void forEachObjectCollision(KosmolotyList kosmolotyList) {
        int currentIndex = 0;

        for (int i = 0; i < kosmolotyList.list.size(); i++) {

            for (int j = i+1; j < kosmolotyList.list.size(); j++) {

                if(kosmolotyList.list.get(i).getPositionX() == kosmolotyList.list.get(j).getPositionX() && kosmolotyList.list.get(i).getPositionY() == kosmolotyList.list.get(j).getPositionY()){
                    collision(kosmolotyList.list.get(i), kosmolotyList.list.get(j));
                }
            }
        }
    }

    private void collision(Kosmolot kosmolot_1, Kosmolot kosmolot_2){
        if(kosmolot_1.getPositionX() == kosmolot_2.getPositionX() && kosmolot_1.getPositionY() == kosmolot_2.getPositionY()){
            kosmolot_1.kill();
            kosmolot_2.kill();
        }
    }
}

class KosmolotyList {
    ArrayList<Kosmolot> list;

    KosmolotyList(Torus torus, String[] args) {
        list = new ArrayList<Kosmolot>();

        Scanner lineScanner = new Scanner(System.in);
        String line;

        int i = 0;
        while (lineScanner.hasNextLine()) {
            String[] kosmolotyRaw;
            line = lineScanner.nextLine();

            kosmolotyRaw = line.split(",");

            if (kosmolotyRaw[4].endsWith("\\n")) {
                kosmolotyRaw[4] = kosmolotyRaw[4].subSequence(0, kosmolotyRaw[4].length() - 2).toString();
            }

            Kosmolot tempKosmolot = new Kosmolot(kosmolotyRaw[0], Integer.parseInt(kosmolotyRaw[1]), Integer.parseInt(kosmolotyRaw[2]), Integer.parseInt(kosmolotyRaw[3]), Integer.valueOf(kosmolotyRaw[4]), torus);
            list.add(tempKosmolot);

            i++;
        }

        if (!checkDataCorectness()) {
            System.out.print("klops");
            System.exit(0);
        }
    }

    private boolean checkDataCorectness() {
        boolean isCorrect = true;

        for (int i = 0; i < list.size(); i++) {

            for (int j = i+1; j < list.size(); j++) {

                if (Objects.equals(list.get(i).getName(), list.get(j).getName())) {
                    isCorrect = false;
                }

                if (list.get(i).getPositionX() == list.get(j).getPositionX() && list.get(i).getPositionY() == list.get(j).getPositionY()) {
                    isCorrect = false;
                }
            }
        }

        return isCorrect;
    }
}


