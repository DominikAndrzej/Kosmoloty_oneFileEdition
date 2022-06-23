import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static java.lang.Math.abs;

public class Kosmolot {
    private String name;
    private int speedX, speedY;
    private int positionX, positionY;
    private int traveledDistance;
    private int traveledDistanceInOneMove;
    private boolean isAlive;

    Kosmolot(String name, int speedX, int speedY, int positionX, int positionY, Torus torus) throws IncorrectDataException {

        this.name = name;
        this.speedX = speedX;
        this.speedY = speedY;
        this.positionX = positionX;
        this.positionY = positionY;
        this.isAlive = true;

        traveledDistanceInOneMove = abs(speedX) + abs(speedY);
        traveledDistance = 0;

        if (!checkDataCorectness(torus)) {
            throw new IncorrectDataException();
        }
    }

    public void changePosition(Torus torus) {
        positionX = positionX + speedX;
        positionY = positionY + speedY;

        traveledDistance += traveledDistanceInOneMove;

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
        if (matcher.matches()) {
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
