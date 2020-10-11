import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;


@WebServlet(name = "A1Servlet")
public class A1Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String download = request.getParameter("download");

        if (download != null)
            downloadMessages(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String post  = request.getParameter("post");
        String clear = request.getParameter("clear");

        if (post != null)
            postMessage(request, response);

        if (clear != null)
            clearMessages(request, response);

        response.sendRedirect(request.getContextPath());
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

    private void postMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user    = request.getParameter("user");
        String message = request.getParameter("message");
        String date    = request.getParameter("date");

        String referer = request.getHeader("Referer");
        if (referer == null) {
            System.err.println("Referer is not present.");
            return;
        }

        ChatManager.PostMessage(user, message, date);
    }

    private void clearMessages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String from = request.getParameter("clear-from");
        String to   = request.getParameter("clear-to");

        String referer = request.getHeader("Referer");
        if (referer == null) {
            System.err.println("Referer is not present.");
            return;
        }

        ChatManager.ClearChat(from, to);
    }

    private String convert_xml(Message message) {
        return "<message>\n"
                + "    <user>" + message.user + "</user>\n"
                + "    <text>" + message.text + "</text>\n"
                + "    <date>" + message.date + "</date>\n"
                + "</message>\n"
                + "\n";
    }
}
