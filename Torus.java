public class Torus {
    private int torusLenX;
    private int torusLenY;

    public Torus(int torusLenX, int torusLenY) throws IncorrectDataException {

        this.torusLenX = torusLenX;
        this.torusLenY = torusLenY;

        if (!checkDataCorectness()) {
            throw new IncorrectDataException();
        }
    }

    public int getTorusLenX() {
        return torusLenX;
    }

    public int getTorusLenY() {
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