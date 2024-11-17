import java.util.LinkedList;
import java.util.Queue;

public class CallCenter {
    private final Queue<Respondent> respondents = new LinkedList<>();
    private final Queue<Manager> managers = new LinkedList<>();
    private final Queue<Director> directors = new LinkedList<>();

    public void addEmployee(Employee employee) {
        if (employee instanceof Respondent) {
            respondents.offer((Respondent) employee);
        } else if (employee instanceof Manager) {
            managers.offer((Manager) employee);
        } else if (employee instanceof Director) {
            directors.offer((Director) employee);
        }
    }

    public Employee getNextAvailableEmployee(int priority){
        if(priority <= 1 && !respondents.isEmpty() && respondents.peek().isAvailable()){
            return respondents.poll();
        } else if(priority <= 2 && !managers.isEmpty() && managers.peek().isAvailable()){
            return managers.poll();
        } else if(!directors.isEmpty() && directors.peek().isAvailable()){
            return directors.poll();
        }
        return null;
    }

    public void returnEmployeeToQueue(Employee employee) {
        if (employee instanceof Respondent) {
            respondents.offer((Respondent) employee);
        } else if (employee instanceof Manager) {
            managers.offer((Manager) employee);
        } else if (employee instanceof Director) {
            directors.offer((Director) employee);
        }
    }

}
