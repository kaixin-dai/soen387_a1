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
        String format_error = (String)session.getAttribute("format-error");
        if(format_error != null) {
            String format_error_message = "The format must be plain-text or xml.";
    %>
        <script type="text/javascript">
            alert("<%= format_error_message %>");
        </script>
    <%
        session.removeAttribute("format_error");
        }
    %>

    <form action="A1Servlet" method="get">
        <label for="download-from">From</label>
        <input type="text" id="download-from" name="download-from">

        <label for="download-to">To</label>
        <input type="text" id="download-to" name="download-to">

        <label for="download-format">Format</label>
        <input type="text" id="download-format" name="download-format">

        <input type="submit" name="download" value="download">
    </form>

    <br>

    <form action="A1Servlet" method="post">
        <label for="clear-from">From</label>
        <input type="text" id="clear-from" name="clear-from">

        <label for="clear-to">To</label>
        <input type="text" id="clear-to" name="clear-to">

        <input type="submit" name="clear" value="clear">
    </form>

    <div class="wrapper">
        <div class="chat_area">
            <ul class="list-unstyled">
                <li class="left clearfix">
                    <div>Jovie</div>
                    <div class="chat-body1_clearfix">
                        <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia.</p>
                        <div class="chat_time-pull-right">09:40PM</div>
                    </div>
                </li>
            </ul>
        </div>

        <form class="message_write" action="A1Servlet" method="post">
            <input type="text" id="user2" name="user" placeholder="Enter your username"> <input type="text" id="date2" name="date" placeholder="Enter date">

            <textarea class="form-control" id="message2" name="message" placeholder="Type a message"></textarea>

            <input class="pull-right btn btn-success" type="submit" name="post" value="post">
        </form>
    </div>
</body>

</html>
