<%-- 
    Document   : statistic
    Created on : Aug 20, 2019, 8:11:22 PM
    Author     : YBolshakova
--%>


<%@page import="com.store.entities.Order"%>
<%@page import="com.store.entities.Goods"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dishes:</title>
    </head>
    <body>
        <h1>Order info:</h1>
    </body>
    <ul>
        <%
            Order order = (Order) request.getAttribute("order");
            if (order != null) {

                out.println("<h1>" + order.toString() + "</h1>");

            } else {
                out.println("<h1>" + "Sorry, we don't have any information yet" + "</h1>");
            }

        %>
        <div>
            <button onclick="location.href = 'index.jsp'">Back to main</button>
        </div>
    </ul>
</html>
