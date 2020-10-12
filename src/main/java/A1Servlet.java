import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "A1Servlet")
public class A1Servlet extends HttpServlet {
    List<Message> list_messages = new ArrayList<Message>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String format = request.getParameter("format");
        ArrayList<Message> messages;

        if(!from.equals("") && !to.equals(""))
            messages = ChatManager.ListMessages(from,to);
        else
            messages = ChatManager.ListMessages();

        String post = "";


        if(format.equals("text")){
            for (int i = 0; i < messages.size(); i++){
                post += messages.get(i).toString() + "\n";
            }
        }

        else if(format.equals("xml")){
            for (int i = 0; i < messages.size(); i++){
                post += convert_xml(messages.get(i));
            }
        }

        else{
            System.out.println("format should be xml or text");
        }

        System.out.print(post);
        response.setContentType("text/xml");
        response.setHeader("Content-Disposition", "attachment; filename=\"messages.txt\"");
        try {
            OutputStream outputStream = response.getOutputStream();
            String outputResult = post;
            outputStream.write(outputResult.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String convert_xml(Message m){
        String xml = "<message>" + "\n"
                    + " <user>"+m.getUser()+"</user>" + "\n"
                    + " <text>"+m.getText()+"</text>" + "\n"
                    + " <date>"+m.getDatestring()+"</date>" + "\n"
                    + "</message>\n\n";

        return xml;

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String user = request.getParameter("user");
        String message = request.getParameter("message");
        String date = request.getParameter("date");

        list_messages.add(new Message(user,message,date));
        System.out.println(user);
        System.out.println(message);
        System.out.println(date);

        String post = "";
        request.setAttribute("list",list_messages);
        request.getRequestDispatcher("index.jsp").forward(request,response);
        //response.setContentType("text/xml");
        //response.setHeader("Content-Disposition", "attachment; filename=\"messages.txt\"");
        try {
            /*
            OutputStream outputStream = response.getOutputStream();
            String outputResult = post;
            outputStream.write(outputResult.getBytes());
            outputStream.flush();
            outputStream.close();
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       list_messages.clear();
       System.out.println("Here");
       request.setAttribute("list",list_messages);
       request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}

