package lift;

import lift.interfaces.iEntrance;
import lift.strategy.LiftMoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Lift {
    private Integer id;
    public Double weight;
    public Integer maxPeople;
    public Floor currentFloor;
    public List<Passenger> passangerList = new ArrayList<>();
    private List<Command> commandList;
    public LiftMoveStrategy strategy;
    public iEntrance entrance;
    public Integer liftSpeed;
    public Lift() {

    }

    public Lift(Integer id, Double weight, Integer maxPeople, Floor currentFloor, List<Command> commandList, iEntrance entrance, Integer liftSpeed) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.maxPeople = maxPeople;
        this.weight = weight;
        this.commandList = commandList;
        this.entrance = entrance;
        this.liftSpeed = liftSpeed;
    }

    public void DoCommand(Command com, List<Floor> floorList) {

        this.currentFloor = floorList.get(com.FloorToGo);
        switch (com.CommandType) {
            case None -> {

            }
            case Take -> {
                List<Passenger> lefters = new ArrayList<>();
                for (Passenger p : currentFloor.waiters
                ) {
                    double weight = 0;
                    for (Passenger psng : passangerList) {
                        weight += psng.weight;
                    }
                    if (passangerList.size() < maxPeople && weight + p.weight < this.weight) {
                        passangerList.add(p);
                    } else {
                        lefters.add(p);
                        entrance.AddToCallList(p.currentFloor);
                    }
                }
                currentFloor.waiters = lefters;


                System.out.println(this.currentFloor.id + " Take, lift: " + this.id);

            }
            case Land -> {

                List<Passenger> toLeave = new ArrayList<>();

                for (Passenger pas : passangerList
                ) {
                    if (pas.destinationFloor == this.currentFloor.id) {
                        toLeave.add(pas);
                    }
                }

                System.out.println(this.currentFloor.id + " Land");
                passangerList = passangerList.stream()
                        .filter(val -> !toLeave.contains(val))
                        .collect(Collectors.toList());

                currentFloor.leavers.addAll(toLeave);

            }
            case LandTake -> {

                List<Passenger> toLeave = new ArrayList<>();

                for (Passenger pas : passangerList
                ) {
                    if (pas.destinationFloor == this.currentFloor.id) {
                        toLeave.add(pas);
                    }
                }

                passangerList = passangerList.stream()
                        .filter(val -> !toLeave.contains(val))
                        .collect(Collectors.toList());

                currentFloor.leavers.addAll(toLeave);

                //land and then take
                List<Passenger> lefters = new ArrayList<>();
                for (Passenger p : currentFloor.waiters
                ) {
                    //sum weight
                    double weight = 0;
                    for (Passenger psng : passangerList) {
                        weight += psng.weight;
                    }

                    //let into lift if is not fat
                    if (passangerList.size() < maxPeople && weight + p.weight < this.weight) {
                        passangerList.add(p);
                    } else {
                        lefters.add(p);
                        entrance.AddToCallList(p.currentFloor);
                    }
                }
                currentFloor.waiters = lefters;
                /////////////////////////////////////////////////

            }
        }
    }

    public boolean Move(List<Command> newCommandList, List<Floor> floorList) {


        this.commandList = newCommandList;

        for (Command com : commandList
        ) {
            try {
                TimeUnit.MILLISECONDS.sleep(liftSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.DoCommand(com, floorList);
        }
        this.commandList.clear();
        return true;
    }


    private boolean canAddPassanger(Passenger passanger) {
        if (passangerList.size() < maxPeople && getPassangersWeight() + passanger.weight <= weight)
            return true;

        return false;
    }

    private void addPerson(Passenger passanger) {
        if (canAddPassanger(passanger))
            passangerList.add(passanger);
    }

    private double getPassangersWeight() {
        return passangerList.stream()
                .map(passanger1 -> passanger1.weight)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private double GetWeight() {
        double x = 0;
        for (Passenger pass : passangerList) {
            x += pass.weight;
        }
        return x;
    }
}
