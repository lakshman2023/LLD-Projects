public class Main {
    public static void main(String[] args) {
        ChatServer server = new ChatServer();

        User alice = server.registerUser("Alice");
        User bob = server.registerUser("Bob");
        User charlie = server.registerUser("Charlie");

        server.userLogin("Alice");
        server.userLogin("Bob");

        ChatRoom group1 = server.createChatRoom("Group1");
        group1.addMember(alice);
        group1.addMember(bob);
        group1.addMember(charlie);

        //ChatRoom group2 = server.createChatRoom("Group2");

        new Thread(server::processMessages).start();

        server.sendMessage(new Message("Alice", "Bob", "Hello, Bob!"));
        server.sendMessage(new Message("Alice", "Charlie", "Hello, Charlie!"));
        server.sendMessage(new Message("Alice", "Group1", "Hello everyone!"));
        server.sendMessage(new Message("Bob", "Alice", "Hi, Alice!"));

        //System.out.println("Hello world!");
    }
}