public abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
        this.isAvailable = true;
    }

    public synchronized boolean isAvailable() {
        return isAvailable;
    }

    public synchronized void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private boolean isAvailable;

    public abstract boolean handleCall(Call call);

}
