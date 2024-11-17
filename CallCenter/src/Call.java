public class Call {
    private final int id;
    private final int priority;

    public int getId() {
        return id;
    }

    public Call(int id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Call " + id + " [Priority: " + priority + "]";
    }
}
