import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private int ID;
    private int currentFloor;
    private List<Level> targetFloor = new ArrayList<>();
    private int temp;

    public Elevator(int ID1, int currentFloor1){
        this.ID=ID1;
        this.currentFloor=currentFloor1;
        this.temp=0;
    }

    public void up(){
        this.currentFloor++;
    }

    public void down(){
        this.currentFloor--;
    }

    public int getID() {
        return ID;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setTargetFloor(Level targetFloor) { this.targetFloor.add(targetFloor); }

    public List<Level> getTargetFloor() { return targetFloor; }

    public int getTemp() { return temp; }

    public void setTemp(int t) { this.temp=t; }

    public void addTemp(){ this.temp+=1; }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }
}
