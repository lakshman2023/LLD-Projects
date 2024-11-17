public class Respondent extends Employee{
    public Respondent(String name){
        super(name);
    }

    @Override
    public boolean handleCall(Call call){
        if(call.getPriority() <= 1){
            System.out.println("Respondent " + getName() + " handled the call." + call);
            return true;
        }
        return false;
    }
}
