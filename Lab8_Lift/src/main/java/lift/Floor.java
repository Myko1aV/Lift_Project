package lift;

import java.util.ArrayList;
import java.util.List;

public class Floor {
    public Integer id;

    public List<Passenger> waiters =  new ArrayList<>();
    public List<Passenger> leavers =  new ArrayList<>();

    public Floor(Integer id) {
        this.id = id;

    }

    public Floor(Integer id, List<Passenger> waiters) {
        this.id = id;

        this.waiters = waiters;
        this.leavers = new ArrayList<>();
    }


    public void addPerson(Passenger person) {
        waiters.add(person);
    }

    public void removePerson(Passenger person) {
        waiters.remove(person);
    }
}
