public class Manager extends Employee{
    public Manager(String name){
        super(name);
    }

    @Override
    public boolean handleCall(Call call){
        if(call.getPriority() <= 2){
            System.out.println("Manager " + getName() + " handled the call."+ call);
            return true;
        }
        return false;
    }
}
