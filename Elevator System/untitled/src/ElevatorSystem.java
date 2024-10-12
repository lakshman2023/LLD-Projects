import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ElevatorSystem {
    UUID elevatorSystemId;
    String elevatorSystemName;
    int noOfFloors;

    public ElevatorSystem(String elevatorSystemName, int noOfFloors) {
        this.elevatorSystemId = UUID.randomUUID();
        this.elevatorSystemName = elevatorSystemName;
        this.elevators = new ArrayList<>();
        this.noOfFloors = noOfFloors;
    }

    List<Elevator> elevators;

    public void addElevator(String elevatorName, int fromFloor, int toFloor, int capacity) {
        Elevator elevator = new Elevator(elevatorName, fromFloor, toFloor, capacity);
        elevators.add(elevator);
        new Thread(() -> elevator.processRequests()).start();
    }

    public void removeElevator(String elevatorName) {
        for (Elevator elevator : elevators) {
            if(elevator.elevatorName.equals(elevatorName)) {
                elevators.remove(elevator);
            }
        }
    }

    public Request requestElevator(int fromFloor, int toFloor) throws NoSuchElementException {
        Elevator optimalElevator = null;
        List<Elevator> idleElevators = elevators.stream().filter(e -> e.selections.size() == 0).toList();
        List<Elevator> upMovingElevators = elevators.stream().filter(e ->  e.currentDirection == Elevator.ElevatorDirection.UP).toList();
        List<Elevator> downMovingElevators = elevators.stream().filter(e ->  e.currentDirection == Elevator.ElevatorDirection.DOWN).toList();
        int minDist = noOfFloors + 1;
        if(fromFloor < toFloor){
            for (Elevator elevator : upMovingElevators) {
                if(elevator.minFloor <= fromFloor && elevator.maxFloor >= toFloor && elevator.requests.size() < elevator.capacity){
                    int diff = Math.abs(elevator.currentFloor - fromFloor);
                    if(minDist > diff){
                        optimalElevator = elevator;
                        minDist = diff;
                    }
                }
            }
        } else {
            for (Elevator elevator : downMovingElevators) {
                if(elevator.minFloor <= fromFloor && elevator.maxFloor >= toFloor && elevator.requests.size() < elevator.capacity){
                    int diff = Math.abs(elevator.currentFloor - fromFloor);
                    if(minDist > diff){
                        optimalElevator = elevator;
                        minDist = diff;
                    }
                }
            }
        }
        for (Elevator elevator : idleElevators) {
            if(elevator.minFloor <= fromFloor && elevator.maxFloor >= toFloor && elevator.requests.size() < elevator.capacity){
                int diff = Math.abs(elevator.currentFloor - fromFloor);
                if(minDist > diff){
                    optimalElevator = elevator;
                    minDist = diff;
                }
            }
        }

        Request request = new Request(fromFloor, toFloor);
        optimalElevator.addRequest(request);
        if(request == null){
            throw new NoSuchElementException("No elevator is operating in the requested direction from your floor.");
        }
        return request;
    }
}
