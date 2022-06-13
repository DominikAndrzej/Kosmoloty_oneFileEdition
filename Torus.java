public class Torus {
    private int torusLenX;
    private int torusLenY;

    public Torus(int torusLenX, int torusLenY) {

        this.torusLenX = torusLenX;
        this.torusLenY = torusLenY;

        if (!checkDataCorectness()) {
            System.out.print("klops Torus");
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