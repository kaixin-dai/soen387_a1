import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "A1Servlet")
public class A1Servlet extends HttpServlet {
    List<Message> list_messages = new ArrayList<Message>();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String download = request.getParameter("download");
        if (download != null)
            downloadMessages(request, response);
    }

    private String convert_xml(Message m){
        String xml = "<message>" + "\n"
                    + " <user>"+m.getUser()+"</user>" + "\n"
                    + " <text>"+m.getText()+"</text>" + "\n"
                    + " <date>"+m.getDatestring()+"</date>" + "\n"
                    + "</message>\n\n";

        return xml;

    }

    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String user = request.getParameter("user");
        String message = request.getParameter("message");
        String date = request.getParameter("date");


        if(message.length()==0){

        }
        else{
            list_messages.add(new Message(user,message,date));
            System.out.println(message);
            System.out.println(date);
            String post = "";
            //response.setContentType("text/xml");
            //response.setHeader("Content-Disposition", "attachment; filename=\"messages.txt\"");
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
        request.setAttribute("list",list_messages);
        request.getRequestDispatcher("index.jsp").forward(request,response);

    }
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("add") != null){
            String user = request.getParameter("user");
            String message = request.getParameter("message");
            String date = request.getParameter("date");
            String post;
            System.out.println("Date is :" + date);

            if(date == null)
                post = ChatManager.PostMessage(user,message);
            else
                post = ChatManager.PostMessage(user,message,date);
            request.setAttribute("list",ChatManager.messages);
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
        else if(request.getParameter("clear")!=null){
            String from = request.getParameter("from");
            String to = request.getParameter("to");

            System.out.println(from);
            System.out.println(to);

            if(!from.equals("") && !to.equals(""))
                ChatManager.ClearChat(from, to);
            else
                ChatManager.ClearChat();
            request.setAttribute("list",ChatManager.messages);
            request.getRequestDispatcher("index.jsp").forward(request,response);
            response.sendRedirect("index.html");
        }

    }
    private void downloadMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from   = request.getParameter("download-from");
        String to     = request.getParameter("download-to");
        String format = request.getParameter("download-format");

        String referer = request.getHeader("Referer");
        if (referer == null) {
            System.err.println("Referer is not present.");
            return;
        }

        ArrayList<Message> messages;
        if (!from.equals("") && !to.equals(""))
            messages = ChatManager.ListMessages(from, to);
        else
            messages = ChatManager.ListMessages();

        HttpSession session = request.getSession();

        StringBuilder messagesStr = new StringBuilder();
        if (format.equals("txt")) {
            for (Message message : messages) {
                messagesStr.append(message.toString());
            }
            response.setHeader("Content-Disposition", "attachment; filename=\"messages.txt\"");
            response.setHeader("Expires", "0");
            response.setContentType("text/plain");
        }
        else if (format.equals("xml")) {
            for (Message message : messages) {
                messagesStr.append(convert_xml(message));
            }

            response.setHeader("Content-Disposition", "attachment; filename=\"messages.xml\"");
            response.setHeader("Expires", "0");
            response.setContentType("text/xml");
        }
        else {
            session.setAttribute("format-error", "format-error");

            response.sendRedirect(request.getContextPath());

            return;
        }

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(messagesStr.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }
}

