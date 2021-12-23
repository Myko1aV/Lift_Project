package lift;

public class Passenger {
   public Integer destinationFloor;
   Integer currentFloor;
   Double weight;
   Double size;

   public Passenger(Double weight, Double size, Integer currentFloor, Integer destinationFloor ){
      this.weight = weight;
      this.size = size;
      this.destinationFloor = destinationFloor;
      this.currentFloor = currentFloor;
   }
   public boolean CallLift(){

     return true;
   }


}
