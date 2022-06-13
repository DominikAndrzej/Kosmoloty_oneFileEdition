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
