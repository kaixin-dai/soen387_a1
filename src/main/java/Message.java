import java.util.Date;

public class Message {
    public String user;
    public String message;
    public Date date;

    Message(String user, String message){
        this.user = user;
        this.message = message;
        date = new Date();
    }

    public String toString() {
        return "Message{" +
                "user='" + user + '\'' +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
