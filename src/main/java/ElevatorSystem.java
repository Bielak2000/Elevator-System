import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ElevatorSystem {
    private final int elevatorsNumber;
    private final int floorsNumber;
    static List<Elevator> elevatorList = new ArrayList<>();

    public ElevatorSystem(int elevatorsNumber1, int floorsNumber1){
        this.elevatorsNumber=elevatorsNumber1;
        this.floorsNumber=floorsNumber1;
        for(int i=0; i<this.elevatorsNumber; i++){
            elevatorList.add(new Elevator(i+1, 0));
        }
    }

    public void summon(int destination){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Application floor: ");
        int applicationFloor = scanner.nextInt();
        int selectedElevator = elevatorSelection(destination, applicationFloor);
        elevatorList.get(selectedElevator-1).setTargetFloor(new Level(applicationFloor, true));
    }

    public int elevatorSelection(int direction, int applicationFloor){
        boolean notFirstElevator=false;
        int temp;
        int min;
        int elevatorID=1;

        if(elevatorList.get(0).getTargetFloor().isEmpty())
            min=Math.abs(elevatorList.get(0).getCurrentFloor()-applicationFloor);
        else
            min=calculateMin(direction, applicationFloor, elevatorList.get(0), elevatorList.get(0).getTargetFloor().get(0).getLevel());

        for(Elevator e : elevatorList){
            if(e.getTargetFloor().size()==0 && notFirstElevator) {
                if (Math.abs(e.getCurrentFloor() - applicationFloor) < min || min == -1) {
                    elevatorID = e.getID();
                    min = Math.abs(e.getCurrentFloor() - applicationFloor);
                }
            }
            else {
                    for (Level target : e.getTargetFloor()) {
                        if (notFirstElevator) {
                            temp = calculateMin(direction, applicationFloor, e, target.getLevel());
                            if ((temp < min && temp != -1) || min == -1) {
                                min = temp;
                                elevatorID = e.getID();
                            }
                        }
                        notFirstElevator=true;
                    }
            }
            notFirstElevator=true;
        }

        return elevatorID;
    }

    public int calculateMin(int direction, int applicationFloor, Elevator e, int currentTargetFloor){
        int temp = currentTargetFloor-e.getCurrentFloor();
        int min= -1;

        if(e.getCurrentFloor()==applicationFloor)
            min=0;
        else if((temp>0 && applicationFloor>e.getCurrentFloor() && applicationFloor<=currentTargetFloor) || (temp<0 && applicationFloor<e.getCurrentFloor() && applicationFloor>=currentTargetFloor))
            min = Math.abs(applicationFloor - e.getCurrentFloor());
        else if (((temp>0 && direction==1) || (temp<0 && direction==-1)) && Math.abs(temp)>=Math.abs(applicationFloor-e.getCurrentFloor()) && e.getTemp()<2) {
            min = Math.abs(applicationFloor - e.getCurrentFloor());
            e.addTemp();
        }
        else if(e.getTemp()<2)
            min = Math.abs(currentTargetFloor-e.getCurrentFloor())+Math.abs(currentTargetFloor-applicationFloor);

        return min;
    }

    public void status(){
        for(Elevator e : elevatorList)
            System.out.println("Elevator ID: " + e.getID() + ", current floor: " + e.getCurrentFloor() + ", target floor: " + e.getTargetFloor().toString());
    }

    public void step() {
        int temp = 0;
        int elevatorTarget;
        int indexTarget;
        boolean notFirstTarget;
        Scanner scanner = new Scanner(System.in);
        for (Elevator e : elevatorList) {
            if (!e.getTargetFloor().isEmpty()) {
                temp = e.getTargetFloor().get(0).getLevel() - e.getCurrentFloor();
                elevatorTarget = e.getTargetFloor().get(0).getLevel();
                indexTarget = 0;
                notFirstTarget = false;
                for (int i = 0; i < e.getTargetFloor().size(); i++) {
                    if (notFirstTarget && temp > Math.abs(e.getTargetFloor().get(i).getLevel() - e.getCurrentFloor())) {
                        temp = Math.abs(e.getTargetFloor().get(i).getLevel() - e.getCurrentFloor());
                        elevatorTarget = e.getTargetFloor().get(i).getLevel();
                        indexTarget = i;
                    }
                    notFirstTarget = true;
                }
                if (e.getCurrentFloor() < elevatorTarget)
                    e.up();
                else if (e.getCurrentFloor() > elevatorTarget)
                    e.down();
                if (e.getCurrentFloor() == elevatorTarget) {
                    if (e.getTargetFloor().get(indexTarget).getStatus()) {
                        System.out.println("Destination floor for " + e.getID() + " elevator: ");
                        int targetFloor = scanner.nextInt();
                        System.out.println();
                        e.setTargetFloor(new Level(targetFloor, false));
                        if (indexTarget == 0)
                            e.setTemp(0);
                    }
                    e.getTargetFloor().remove(indexTarget);
                }
            }
        }
    }
    public List<Elevator> getElevatorList() {
        return elevatorList;
    }
}