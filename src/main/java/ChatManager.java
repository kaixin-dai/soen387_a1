import java.util.Date;
import java.util.ArrayList;

public class ChatManager {

    public static ArrayList<Message>messages = new ArrayList<Message>();
    //todo anonymous user
    public static String PostMessage(String user, String message){

        Message m = new Message(user,message);

        messages.add(m);

        return "User: " + m.user+"Message: " + m.message + " "+ m.date;
    }


    //todo Date comparsion for List and Clear
    public static ArrayList<Message> ListMessages(Date from, Date to){
        ArrayList<Message>found_messages = new ArrayList<Message>();
        for (int i = 0; i < messages.size(); i++) {

//            if(messages.get(i).date.after(from) && messages.get(i).date.before(to)){

                found_messages.add(messages.get(i));

//            }
        }
        return found_messages;

    }

    public static void ClearChat(Date from, Date to){
        ArrayList<Message>found_messages = new ArrayList<Message>();

        for (int i = 0; i < messages.size(); i++) {

//            if(messages.get(i).date.after(from) && messages.get(i).date.before(to)){

            found_messages.add(messages.get(i));

//            }
        }
        messages.removeAll(found_messages);
    }




    public static void main(String[] args) {
        System.out.print(ChatManager.PostMessage("rinima", "dajiba"));
        System.out.print(ChatManager.PostMessage("shabi", "nima"));


        ArrayList<Message> a = ListMessages(null,null);

        for (int i = 0; i < a.size() ; i++){
            System.out.println(a.get(i));
        }

        ClearChat(null,null);
        System.out.print("showing messages after deleting");
        ArrayList<Message> b = ListMessages(null,null);

        for (int i = 0; i < b.size() ; i++){
            System.out.println(b.get(i));
        }
        System.out.print("finish");






    }
}
