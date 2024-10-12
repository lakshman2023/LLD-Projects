public class Main {
    public static void main(String[] args) throws InterruptedException {
        int noOfFloors = 10;
        ElevatorSystem elevatorSystem = new ElevatorSystem("AMB", noOfFloors);
        for(int i=1; i<=2; i++) {
            elevatorSystem.addElevator(
                    "elevator " + i,
                    0,
                    noOfFloors,
                    10
            );
        }

        Request request3 = elevatorSystem.requestElevator(4,0);
        Thread.sleep(2000);
        Request request1 = elevatorSystem.requestElevator(0,10);
        Thread.sleep(2000);
        Request request2 = elevatorSystem.requestElevator(3,8);
        //        Request request3 = elevatorSystem.requestElevator(4,0);
        Request request4 = elevatorSystem.requestElevator(5,3);
//        Request request5 = elevatorSystem.requestElevator(0,9);
//        Request request6 = elevatorSystem.requestElevator(3,10);
//        Request request7 = elevatorSystem.requestElevator(9,4);
//        Request request8 = elevatorSystem.requestElevator(2,4);
    }
}