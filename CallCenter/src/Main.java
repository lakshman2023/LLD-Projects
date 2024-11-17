import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        CallCenter callCenter = new CallCenter();

        callCenter.addEmployee(new Respondent("Alice"));
        callCenter.addEmployee(new Respondent("Bob"));
        callCenter.addEmployee(new Manager("Charlie"));
        callCenter.addEmployee(new Director("Diana"));

        CallDispatcher dispatcher = new CallDispatcher(callCenter);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            int callId = i;
            executorService.submit(() -> {
                Call call = new Call(callId, (int) (Math.random() * 3 ) + 1);
                System.out.println("Incoming Call: " + call);
                dispatcher.dispatch(call);
            });
        }
        executorService.shutdown();
    }
}