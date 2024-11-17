import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

public class ChatServer {
    private Map<String, User> users;
    private BlockingDeque<Message> messages;
    private Map<String, ChatRoom> chatRooms;

    public ChatServer() {
        this.users = new ConcurrentHashMap<>();
        this.messages = new LinkedBlockingDeque<>();
        this.chatRooms = new ConcurrentHashMap<>();
    }

    public User registerUser(String username) {
        User user = new User(username);
        this.users.put(username, user);
        return user;
    }

    public void userLogin(String username){
        User user = this.users.get(username);
        if(user != null){
            user.setOnline(true);
        }
    }

    public void userLogout(String username){
        User user = this.users.get(username);
        if(user != null){
            user.setOnline(false);
        }
    }

    public ChatRoom createChatRoom(String roomName){
        ChatRoom room = new ChatRoom(roomName);
        this.chatRooms.put(roomName, room);
        return room;
    }

    public void sendMessage(Message message){
        messages.offer(message);
    }

    public void processMessages(){
        while(true){
            try{
                Message message = messages.take();
                deliverMessage(message);
                // boolean delivered = deliverMessage(message);
//                if(!delivered){
//                    messages.offer(message);//retry
//                }
            } catch (InterruptedException e){
                System.out.println("Something went wrong");
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    private boolean deliverMessage(Message message){
        User receiver = this.users.get(message.getReceiver());
        if(receiver != null && receiver.isOnline()){
            System.out.println("Delivered message " + message.getMessage() + " to " + receiver.getUsername() + " from " + message.getSender());
            return true;
        } else if(chatRooms.containsKey(message.getReceiver())){
            ChatRoom room = chatRooms.get(message.getReceiver());
            for(User user : room.getMembers()){
                if(user.isOnline()){
                    System.out.println("Delivered message "  + message.getMessage() + " to " + user.getUsername() + " in group " + room.getName() + " from " + message.getSender());
                }
            }
            return true;
        } else {
            System.out.println("Receiver not available for sending message");
        }
        return false;
    }
}
