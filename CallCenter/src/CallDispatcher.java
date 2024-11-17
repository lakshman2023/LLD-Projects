public class CallDispatcher {
    private final CallCenter callCenter;

    public CallDispatcher(CallCenter callCenter) {
        this.callCenter = callCenter;
    }

    public void dispatch(Call call) {
        new Thread(() -> {
            Employee employee = null;
            while (employee == null) {
                employee = callCenter.getNextAvailableEmployee(call.getPriority());
                if(employee != null){
                    employee.setAvailable(false);
                    boolean handled = employee.handleCall(call);
                    if(!handled){//not handled, try next level
                        System.out.println(call + " is being escalated.");
                        callCenter.returnEmployeeToQueue(employee);
                        employee = null;
                    } else {
                        System.out.println(call + " has been handled by " + employee.getName());
                        callCenter.returnEmployeeToQueue(employee);
                    }
                    employee.setAvailable(true);
                } else {
                    try{
                        System.out.println(call + " is waiting to be handled.");
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
