import java.util.UUID;

public class Request {
    UUID requestId;

    public Request(int fromFloor, int toFloor) {
        this.requestId = UUID.randomUUID();
        this.fromFloor = fromFloor;
        this.destinationFloor = toFloor;
        this.direction = (fromFloor < destinationFloor) ? Elevator.ElevatorDirection.UP : Elevator.ElevatorDirection.DOWN;;
    }

    int fromFloor;
    int destinationFloor;
    Elevator.ElevatorDirection direction;
}
