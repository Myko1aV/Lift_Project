package lift;
import lift.interfaces.iEntrance;
import lift.logger.LiftLogger;
import lift.strategy.LiftMoveStrategy;
import lift.strategy.Strategy1;
import lift.strategy.Strategy2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Entrance extends Thread implements iEntrance {
    public Integer id;
    public Lift lift;
    public List<Floor> floorList = new ArrayList<>();
    public List<Integer> callList = Collections.synchronizedList(new ArrayList<Integer>());
    public LiftMoveStrategy liftMoveStrategy = new Strategy1();
    public List<Command> newCommandList = new ArrayList<>();

    public void setStrategy(LiftMoveStrategy liftMoveStrategy){
        this.liftMoveStrategy = liftMoveStrategy;
    }


    public Entrance(Integer floorCount, Integer id, Integer liftSpeed) {
        this.id = id;
        CreateFloors(floorCount);
        this.lift = new Lift(id, (double) Config.getLiftMaxWeight(), Config.getGetLiftMaxCount(), floorList.get(0), null, this, liftSpeed);
    }

    public void CreateFloors(Integer floorCount) {
        for (int i = 0; i < floorCount; i++) {
            floorList.add(new Floor(i));
        }
    }

    public void CreatePasseger(Integer floor, Integer destinationFloor) {
        //System.out.println("new passager");
        Floor fl = floorList.stream().filter(x -> x.id == floor).findFirst().orElse(null);
        if (fl != null) {
            fl.waiters.add(new Passenger(60.0, 2.0, floor, destinationFloor));
            AddToCallList(floor);
            this.newCommandList = liftMoveStrategy.CreateCommandList(callList, lift.currentFloor, floorList);
        }
    }

    public void AddToCallList(Integer i) {
        if (callList.contains(i) == false) {
           // System.out.println("tid: " + Thread.currentThread().getId() + ") new call size: " + callList.size());
            callList.add(i);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!callList.isEmpty()){
                this.newCommandList = liftMoveStrategy.CreateCommandList(callList, lift.currentFloor, floorList);
            }
            if (!newCommandList.isEmpty()) {
                LiftLogger.fine("Lift #" + Thread.currentThread().getId() +" - Do commands");

                lift.Move(newCommandList, floorList);
            } else{
                LiftLogger.fine("Lift #" + Thread.currentThread().getId() +" - Stop");
            }
        }
    }
}
