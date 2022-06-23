public class Collider {
    public void forEachObjectCollision(KosmolotyList kosmolotyList) {

        for (Kosmolot i : kosmolotyList.list) {
            for (Kosmolot j : kosmolotyList.list) {
                if (i.getPositionX() == j.getPositionX() && i.getPositionY() == j.getPositionY() && i != j) {
                    collision(i, j);
                }
            }
        }
    }

    private void collision(Kosmolot kosmolot_1, Kosmolot kosmolot_2) {
        if (kosmolot_1.getPositionX() == kosmolot_2.getPositionX() && kosmolot_1.getPositionY() == kosmolot_2.getPositionY()) {
            kosmolot_1.kill();
            kosmolot_2.kill();
        }
    }
}