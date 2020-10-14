<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
    String referer_error = (String)session.getAttribute("referer-error");
    session.removeAttribute("referer-error");

    String format_error = (String)session.getAttribute("format-error");
    session.removeAttribute("format-error");

    String theme = (String)session.getAttribute("theme");
    if (theme == null)
        theme = "blue";
%>


<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">

    <title>Chat Room</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <%= "<link rel=\"stylesheet\" href=\"" + (theme.equals("blue") ? "blue-theme.css" : "green-theme.css") + "\">" %>
</head>

<body>
    <script type="text/javascript">
        <%= referer_error != null ? "alert(\"" + referer_error + "\");" : "" %>
        <%=  format_error != null ? "alert(\"" +  format_error + "\");" : "" %>
    </script>

    <form action="A1Servlet" method="POST" class="switch-theme-form">
        <input class="btn btn-secondary" type="submit" name="switch-theme" value="Switch Theme">
        <%= "<img src=\""+(theme.equals("blue") ? "crlogo.png" : "logo.png") + "\" width=\"5%\" height=\"10%\" alt=\"Logo\" class=\"logo\"> "%>
    </form>

    <div class="wrapper">
        <form action="A1Servlet" method="GET" class="get-post-form">
            <div class="form-row">
                <div class="form-group col-4">
                    <label for="download-from" class="sr-only">From</label>
                    <input type="text" class="form-control" id="download-from" name="download-from" placeholder="From">
                </div>

                <div class="form-group col-4">
                    <label for="download-to" class="sr-only">To</label>
                    <input type="text" class="form-control" id="download-to" name="download-to" placeholder="To">
                </div>

                <div class="form-group col-2">
                    <label for="download-format" class="sr-only">Format</label>
                    <input type="text" class="form-control format" id="download-format" name="download-format" value="txt">
                </div>

                <div class="form-group col-2">
                    <input class="btn btn-clear" type="submit" name="download" value="Download History">
                </div>
            </div>
        </form>

        <form action="A1Servlet" method="POST" class="get-post-form">
            <div class="form-row">
                <div class="form-group col-5">
                    <label for="clear-from" class="sr-only">From</label>
                    <input type="text" class="form-control" id="clear-from" name="clear-from" placeholder="From">
                </div>

                <div class="form-group col-5">
                    <label for="clear-to" class="sr-only">To</label>
                    <input type="text" class="form-control" id="clear-to" name="clear-to" placeholder="To">
                </div>

                <div class="form-group col-2">
                    <input type="submit" class="btn btn-clear" name="clear" value="Clear History">
                </div>
            </div>
        </form>

        <ul class="list-unstyled chat-area">
            <c:forEach var="message" items="${messages}">
                <li>
                    <div>${message.user}</div>
                    <div class="chat-body">${message.text}</div>
                    <div class="chat-time">${message.dateString}</div>
                </li>
            </c:forEach>
        </ul>

        <form action="A1Servlet" method="POST" class="get-post-form">
            <div class="form-row">
                <div class="form-group col-12">
                    <label for="message" class="sr-only">Type a message</label>
                    <textarea class="form-control" id="message" name="message" placeholder="Type a message"></textarea>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-4">
                    <label for="user" class="sr-only">Username</label>
                    <input type="text" class="form-control" id="user" name="user" placeholder="Username">
                </div>

                <div class="form-group col-1">
                    <%= "<input class=\"btn " + (theme.equals("blue") ? "btn-primary" : "btn-success") + "\" type=\"submit\" name=\"post\" value=\"Post\">" %>
                </div>

                <div class="form-group col-1 offset-6">
                    <%= "<input class=\"btn " + (theme.equals("blue") ? "btn-primary" : "btn-success") + "\" type=\"submit\" name=\"refresh\" value=\"Refresh Page\">" %>
                </div>
            </div>
        </form>
    </div>
</body>

</html>
