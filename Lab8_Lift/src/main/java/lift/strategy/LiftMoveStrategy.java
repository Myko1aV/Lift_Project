package lift.strategy;

import lift.Command;
import lift.Floor;

import java.util.List;

public interface LiftMoveStrategy {

    public List<Command> CreateCommandList(List<Integer> callLift, Floor currentFloor, List<Floor> floorList);
}
