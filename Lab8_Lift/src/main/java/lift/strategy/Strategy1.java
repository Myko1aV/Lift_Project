package lift.strategy;

import lift.Command;
import lift.Floor;
import lift.Passenger;
import lift.enums.CommandType;
import lift.logger.LiftLogger;

import java.util.ArrayList;
import java.util.List;

public class Strategy1 implements LiftMoveStrategy {

    @Override
    public List<Command> CreateCommandList(List<Integer> callLift, Floor currentFloor, List<Floor> floorList) {
        List<Command> commandList = new ArrayList<>();
        LiftLogger.fine("Lift #" + Thread.currentThread().getId() +" moved");
        while(callLift.size() != 0) {
            var currentFlor = currentFloor.id;

            Integer fl = callLift.get(0);

            //setMiddleFloors(commandList, fl, currentFlor);

            commandList.add(new Command(fl, CommandType.Take));

            Floor floor = floorList.get(fl);

            List<Passenger> pass = floor.waiters;

            List<Integer> floors;
            floors = pass.stream().map(x -> x.destinationFloor).distinct().toList();

            //setMiddleFloors(commandList, floors.get(0), fl);

            for (int i = 0 ;i < floors.size(); i+=1)
             {
                 LiftLogger.fine("Lift #" + Thread.currentThread().getId() +" - Land");
                 if(i == floors.size() - 1){
                     commandList.add(new Command(floors.get(i), CommandType.Land));
                 } else {
                     commandList.add(new Command(floors.get(i), CommandType.Land));
                     //setMiddleFloors(commandList, floors.get(i + 1), floors.get(i));
                     //commandList.add(new Command(floors.get(i + 1), CommandType.Land));
                 }


            }

            // DeleteSame(commandList);

            LiftLogger.fine("Lift #" + Thread.currentThread().getId() +"Remove");
            System.out.println("Remove");
            callLift.remove(0);
        }
        callLift.clear();
        return commandList;
    }

    public void setMiddleFloors(List<Command> commandList, int floorToGo, int currentFloor) {
        boolean isGoingUp = floorToGo - currentFloor > 0 ? true : false;

        if(isGoingUp) {
            for (int i = currentFloor + 1; i < floorToGo; i++) {
                commandList.add(new Command(i, CommandType.None));
            }

        } else {
            for(int i = currentFloor - 1; i< floorToGo; i--){
                commandList.add(new Command(i, CommandType.None));
            }
        }
    }

//    public void DeleteSame(List<Command> commandList){
//        boolean cont = true;
//        while(cont) {
//            for (int i = 0; i < commandList.size() - 1; i++) {
//                if (commandList.get(i).FloorToGo == commandList.get(i + 1).FloorToGo) {
//                    cont = true;
//                    commandList.remove(i);
//                }
//            }
//            if(cont == true){
//                cont = true;
//            } else {
//                cont = false;
//            }
//        }
//    }
}
