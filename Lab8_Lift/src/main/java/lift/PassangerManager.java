package lift;

import java.util.concurrent.TimeUnit;

public class PassangerManager extends Thread {

    Entrance entrance;
    public boolean stop = false;
    public long speed ;
    public PassangerManager(Entrance entrance,Long speed){
        this.entrance = entrance;
        this.speed = speed;
    }

    @Override
    public void run() {

        while (!stop) {

            int time = (int) speed;

            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int floorAmount = entrance.floorList.size();
            int floor = (int)Math.floor(Math.random() * (floorAmount) + 0);
            int destinationFloor = (int)Math.floor(Math.random() * (floorAmount) + 0);

            while (floor == destinationFloor) {
                floor = (int)Math.floor(Math.random() * (floorAmount) + 0);
                destinationFloor = (int)Math.floor(Math.random() * (floorAmount) + 0);
            }

            entrance.CreatePasseger(floor, destinationFloor);

        }
    }
}
