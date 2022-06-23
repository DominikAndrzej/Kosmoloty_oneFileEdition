public class Kosmoloty {
    public static void main(String[] args) {

        Torus torus = null;
        KosmolotyList kosmoloty = null;
        try {
            torus = new Torus(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            kosmoloty = new KosmolotyList(torus, args);
        } catch (IncorrectDataException exception) {
            System.out.print(exception.getMessage());
            System.exit(0);
        }

        Collider collider = new Collider();

        int raceLength = 86400;

        for (int i = 0; i < raceLength; i++) {
            for (Kosmolot j : kosmoloty.list) {
                j.changePosition(torus);
            }

            collider.forEachObjectCollision(kosmoloty);
        }

        System.out.print(findWinner(kosmoloty));

        System.exit(0);
    }

    static String findWinner(KosmolotyList kosmolotyList) {
        int firstCandidateIndex = 0;

        while (!kosmolotyList.list.get(firstCandidateIndex).getIsAlive()) {
            firstCandidateIndex++;
            if (firstCandidateIndex == kosmolotyList.list.size()) {
                System.out.print("remis");
                System.exit(0);
            }
        }

        Kosmolot winner = kosmolotyList.list.get(firstCandidateIndex);

        for (Kosmolot i : kosmolotyList.list) {
            if (i.getTraveledDistance() > winner.getTraveledDistance() && i.getIsAlive() && i != winner) {
                winner = i;
            }
        }

        for (Kosmolot i : kosmolotyList.list) {
            if (winner.getTraveledDistance() == i.getTraveledDistance() && i.getIsAlive() && i != winner) {
                System.out.print("remis");
                System.exit(0);
            }
        }

        return winner.getName();
    }
}
