import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    ElevatorSystem elevatorSystem;

    @Before
    public void beforeAll(){
        elevatorSystem=new ElevatorSystem(16,10);
        for(int i=0; i<4; i++)
            elevatorSystem.getElevatorList().get(0).setTargetFloor(new Level(10-i, true));
    }

    @After
    public void tearDown(){
        elevatorSystem.getElevatorList().get(0).getTargetFloor().clear();
        elevatorSystem.getElevatorList().get(0).setCurrentFloor(0);
    }

    @Test
    public void SelectedElevator_first(){
        // when
        int selectedElevator = elevatorSystem.elevatorSelection(1, 0);

        // then
        assertEquals(selectedElevator, 1);
    }

    @Test
    public void SelectedElevator_second(){
        // given
        for(int i=0; i<4; i++){
            elevatorSystem.step();
        }

        // when
        int selectedElevator = elevatorSystem.elevatorSelection(1, 0);

        // then
        assertEquals(selectedElevator, 2);
    }
}
