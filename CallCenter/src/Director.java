public class Director extends Employee {
    public Director(String name){
        super(name);
    }

    @Override
    public boolean handleCall(Call call){
        System.out.println("Director " + getName() + " handled the call " + call);
        return true;
    }
}
