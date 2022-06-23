import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class KosmolotyList {
    ArrayList<Kosmolot> list;

    KosmolotyList(Torus torus, String[] args) throws IncorrectDataException {
        list = new ArrayList<Kosmolot>();

        //Scanner lineScanner = new Scanner(System.in);
        String line;

        int i = 2;
        while (i < args.length/*lineScanner.hasNextLine()*/) {
            String[] kosmolotyRaw;
            line = args[i];
            //lineScanner.nextLine();

            kosmolotyRaw = line.split(",");

            if (kosmolotyRaw[4].endsWith("\\n")) {
                kosmolotyRaw[4] = kosmolotyRaw[4].subSequence(0, kosmolotyRaw[4].length() - 2).toString();
            }

            Kosmolot tempKosmolot = new Kosmolot(kosmolotyRaw[0], Integer.parseInt(kosmolotyRaw[1]), Integer.parseInt(kosmolotyRaw[2]), Integer.parseInt(kosmolotyRaw[3]), Integer.valueOf(kosmolotyRaw[4]), torus);
            list.add(tempKosmolot);

            i++;
        }

        if (!checkDataCorectness()) {
            throw new IncorrectDataException();
        }
    }

    private boolean checkDataCorectness() {
        for (Kosmolot i : list) {

            for (Kosmolot j : list) {
                if (Objects.equals(i.getName(), j.getName()) && i != j) {
                    return false;

                }
                if (i.getPositionX() == j.getPositionX() && i.getPositionY() == j.getPositionY() && i != j) {
                    return false;
                }
            }
        }
        return true;
    }
}