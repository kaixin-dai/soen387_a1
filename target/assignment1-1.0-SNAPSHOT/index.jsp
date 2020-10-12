<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>



<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="UTF-8">

    <title>index</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="chat.css">
</head>

<body>
    <%
        String referer_error = (String)session.getAttribute("referer-error");
        if(referer_error != null) {
    %>
    <script type="text/javascript">
        alert("<%= referer_error %>");
    </script>
    <%
            session.removeAttribute("format_error");
        }
    %>

    <%
        String format_error = (String)session.getAttribute("format-error");
        if(format_error != null) {
    %>
            <script type="text/javascript">
                alert("<%= format_error %>");
            </script>
    <%
            session.removeAttribute("format_error");
        }
    %>

    <div class="wrapper">
        <div class="chat_area">
            <ul class="list-unstyled">
                <c:forEach var="message" items="${messages}" >
                    <li class="left clearfix">
                        <div>${message.user}</div>
                        <div class="chat-body1_clearfix"><p>${message.text}</p></div>
                        <div class="chat_time-pull-right">${message.dateString}</div>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <form class="message_write" action="A1Servlet" method="post">
            <input type="text" name="user" placeholder="Enter your username">

            <textarea class="form-control" name="message" placeholder="Type a message"></textarea>

            <input class="pull-right btn btn-success" type="submit" name="post" value="post">

            <input class="pull-right btn btn-success" type="submit" name="refresh" value="refresh">
        </form>

        <form action="A1Servlet" method= "POST" id="deletehistory">
            <label for="from_delete">From:</label>
            <input id="from_delete" name="clear-from" type = "text">
            <label for="to_delete">To:</label>
            <input id="to_delete" name="clear-to" type = "text">
            <input name="clear" class="pull-left btn btn-clear" value="Clear History" type="submit">
        </form>

        <form action="A1Servlet" method= "GET">
            <label for="download-from">From</label>
            <input type="text" id="download-from" name="download-from">

            <label for="download-to">To</label>
            <input type="text" id="download-to" name="download-to">

            <label for="download-format">Format</label>
            <input type="text" id="download-format" name="download-format">

            <input class="pull-left btn btn-clear" type="submit" name="download" value="Download History">
        </form>
    </div>
</body>

</html>
