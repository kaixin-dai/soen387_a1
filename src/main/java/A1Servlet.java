import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "A1Servlet")
public class A1Servlet extends HttpServlet {
    
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("add") != null){

            String user = request.getParameter("user");
            String message = request.getParameter("message");
            String date = request.getParameter("date");
            String post;

            System.out.println("Date is :" + date);

            if(date.equals(""))
                post = ChatManager.PostMessage(user,message);
            else
                post = ChatManager.PostMessage(user,message,date);

//            request.setAttribute("list",ChatManager.messages);
//            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

        else if(request.getParameter("clear") != null){
            String from = request.getParameter("from");
            String to = request.getParameter("to");

            System.out.println(from);
            System.out.println(to);

            if(!from.equals("") && !to.equals(""))
                ChatManager.ClearChat(from, to);
            else
                ChatManager.ClearChat();
        }


        response.sendRedirect("index.html");

    }
    
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
                    + " <user>"+m.user+"</user>" + "\n"
                    + " <text>"+m.text+"</text>" + "\n"
                    + " <date>"+m.date+"</date>" + "\n"
                    + "</message>\n\n";

        return xml;

    }

}

