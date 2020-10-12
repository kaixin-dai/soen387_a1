<%--
  Created by IntelliJ IDEA.
  User: lllll
  Date: 2020-10-11
  Time: 3:00 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% response.setHeader("Cache-Control","no-cache");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="chat.css">
    <script src="frontend.js" type="text/javascript"></script>
</head>
<body>
<script type="text/javascript">
    /*
    const interval = setInterval(function() {
        document.getElementById('submitform').submit();
    }, 1000)
    //clearInterval(interval);
    */
</script>
<!--
<form action = "A1Servlet" method="POST">
    User <input type="text" name = "user">
    <br>
    message <textarea name="message" rows="4" cols="50"></textarea>
    <br>
    time<input type="text" name = "date">
    <br>
    from <input type="text" name = "from">
    <br>
    to <input type="text" name = "to">
    <br>
    from <input type="text" name = "from">
    <br>
    to <input type="text" name = "to">
    <br>
    format<input type="text" name = "format">
    <br>
    <input type="submit" value="add" name="add">
</form>

<form action = "A1Servlet" method="POST">

    <input type="submit" value="clear" name="clear">
</form>

<form action = "A1Servlet" method="POST">
   <input type="submit" value="download">
</form>
-->
<div class = "wrapper">

    <div class="chat_area">
        <div>${list_message[0].text}</div>
        <ul class="list-unstyled">
        <c:forEach var="message" items="${list}" >
                <li class="left clearfix">
                    <div>${message.user}</div>
                    <div class="chat-body1_clearfix">
                        <p>${message.text}</p
                    </div>
                    <div class="chat_time-pull-right">${message.datestring}</div>
                </li>
        </c:forEach>
        </ul>
    </div><!--chat_area-->

    <div class="message_write">
        <textarea name = "message" form = "submitform" class="form-control" placeholder="type a message"></textarea>
        <div class="clearfix"></div>
        <div class="chat_bottom">
            <form action="A1Servlet" method = "POST" id="submitform">
                <label for="user">User: </label>
                <input id="user" name = "user" type = "text">
                <input name="add" type = "submit" class="pull-right btn btn-success">
                <input style="visibility: hidden"  name=date type="text" value="2020--10--11" value = "Send">
            </form>
            <form action="A1Servlet" method= "POST" id="deletehistory">
                <label for="from_delete">From:</label>
                <input id="from_delete" name = "from" type = "text">
                <label for="to_delete">To:</label>
                <input id="to_delete" name = "to" type = "text">
                <input name="clear" class="pull-left btn btn-clear" value="Clear Chat History" type="submit">
            </form>
            <form action="A1Servlet" method= "GET" id="searchhistory">
                <label for="from_search">From:</label>
                <input id="from_search" name = "from" type = "text">
                <label for="to_search">To:</label>
                <input id="to_search" name = "to" type = "text">
                <label for="format">Format:</label>
                <input id="format" name = "format" type = "text">
                <input name="download" class="pull-left btn btn-clear" value="Download History" type="submit">
            </form>

            <!-- <script type="text/javascript"> attachFormSubmitEvent("deletehistory" )</script> -->
        </div>
    </div>

</div>

</body>
</html>
