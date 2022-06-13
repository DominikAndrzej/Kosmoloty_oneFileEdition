public class Collider {
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