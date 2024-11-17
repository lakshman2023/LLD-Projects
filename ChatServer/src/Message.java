public class Message {
    private String sender;
    private String receiver;
    private String message;
    private long timestamp;

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Message(String sender, String receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "Message [sender=" + sender + ", receiver=" + receiver + ", message=" + message + ", timestamp=" + timestamp + "]";
    }
}
