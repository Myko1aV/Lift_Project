package lift;

import lift.enums.CommandType;

public class Command {
    public int FloorToGo;
    public CommandType CommandType;

    public Command(int FloorToGo, CommandType CommandType) {
        this.FloorToGo = FloorToGo;
        this.CommandType = CommandType;
    }
}

