import java.util.HashSet;
import java.util.Set;

public class ChatRoom {
    private String name;
    private Set<User> members;
    public ChatRoom(String name) {
        this.name = name;
        this.members = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public Set<User> getMembers() {
        return members;
    }

    public void removeMember(User user) {
        members.remove(user);
    }


}
