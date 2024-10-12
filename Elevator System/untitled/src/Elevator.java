import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Elevator {
    String elevatorId;
    String elevatorName;
    int currentFloor;
    int minFloor;
    int maxFloor;
    int capacity;
    List<Request> requests;
    List<Integer> selections;
    ElevatorDirection currentDirection;
    enum ElevatorDirection {
        UP,
        DOWN
    }

    public Elevator(String elevatorName, int fromFloor, int toFloor, int capacity) {
        this.elevatorId = UUID.randomUUID().toString();
        this.elevatorName = elevatorName;
        this.capacity = capacity;
        this.minFloor = fromFloor;
        this.currentFloor = this.minFloor;
        this.maxFloor = toFloor;
        this.currentDirection = ElevatorDirection.UP;
        this.requests = new ArrayList<>();
        this.selections = new ArrayList<>();
    }

    public void addRequest(Request request) {//to add new request and notify
        synchronized (requests) {
            this.requests.add(request);
            requests.notify();
        }
    }

    public void processRequests() {//elevator keeps moving to complete all requests
        while(true){
            synchronized (requests) {
                try {
                    if(requests.isEmpty() && selections.isEmpty()){//wait for requests
                        requests.wait();
                        System.out.println("Elevator " + elevatorId + " is waiting for requests at " + currentFloor);
                    } else {
                        addSelectionsAtCurrentFloor(currentFloor);//add passengers at curr floor
                        System.out.println("Elevator " + elevatorName + " is at " + currentFloor +" with " + selections.size() + " people.");
                        completeSelections(currentFloor);
                        boolean hasUpRequests = requests.stream().anyMatch(e -> e.fromFloor > currentFloor);
                        boolean hasDownRequests = requests.stream().anyMatch(e -> e.fromFloor < currentFloor);
                        boolean hasUpSelections = selections.stream().anyMatch(e -> e > currentFloor);
                        boolean hasDownSelections = selections.stream().anyMatch(e -> e < currentFloor);
                        boolean shouldMoveUp = hasUpRequests || hasUpSelections;
                        boolean shouldMoveDown = hasDownRequests || hasDownSelections;
                        if(currentDirection == ElevatorDirection.UP && currentFloor < maxFloor && shouldMoveUp){
                            currentFloor++;
                            if(currentFloor == maxFloor){
                                currentDirection = ElevatorDirection.DOWN;
                            }
                            requests.wait(1000);
                        } else if(currentDirection == ElevatorDirection.DOWN && currentFloor > minFloor && shouldMoveDown) {
                            currentFloor--;
                            if (currentFloor == minFloor) {
                                currentDirection = ElevatorDirection.UP;
                            }
                            requests.wait(1000);
                        } else if(shouldMoveDown){
                            currentDirection = ElevatorDirection.DOWN;
                        } else if(shouldMoveUp) {
                            currentDirection = ElevatorDirection.UP;
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void addSelectionsAtCurrentFloor(int currentFloor) {
        List<Request> currFloorRequests = this.requests.stream().filter(e -> e.fromFloor == currentFloor).toList();
        requests.removeIf(e -> e.fromFloor == currentFloor);
        selections.addAll(currFloorRequests.stream().map(e -> e.destinationFloor).toList());
    }

    private void completeSelections(int currentFloor){
        int currFloorRequests = selections.stream().filter(e-> e == currentFloor).toList().size();
        selections.removeIf(e -> e.equals(currentFloor));
        if(currFloorRequests > 0){
            System.out.println("Elevator " + elevatorName + " completed request to " + currentFloor +" and has " + selections.size() + " people.");
        }
    }
}
