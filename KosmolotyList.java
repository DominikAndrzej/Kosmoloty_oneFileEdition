import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;


public class KosmolotyList {
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
            System.out.print("klops List");
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