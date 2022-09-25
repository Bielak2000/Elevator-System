import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            ElevatorSystem elevatorSystem = new ElevatorSystem(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            for(;;)
                menu(elevatorSystem);
        }catch (Exception e){
            System.out.println("Bad arguments!");
        }
    }

    public static void menu(ElevatorSystem elevatorSystem){
        System.out.println("Choose a option:");
        System.out.println("1. Move UP");
        System.out.println("2. Move down");
        System.out.println("3. Show elevators status");
        System.out.println("4. Next step");
        System.out.println("5. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                elevatorSystem.summon(1);
                System.out.println();
                break;
            case 2:
                elevatorSystem.summon(-1);
                System.out.println();
                break;
            case 3:
                elevatorSystem.status();
                System.out.println();
                break;
            case 4:
                elevatorSystem.step();
                System.out.println();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong choice!");
                System.out.println();
                break;
        }
    }
}
