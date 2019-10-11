<%-- 
    Document   : add
    Created on : 30.09.2019, 0:52:31
    Author     : Lenovo
--%>

<%@page import="com.store.entities.Order"%>
<%@page import="com.store.entities.Goods"%>
<%@page import="com.store.entities.Client"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Get order</title>
    </head>
    <body>

        <%
            List<Order> orders = (List<Order>) request.getAttribute("list");            
            out.println("<form action=\"getOrder\" method=\"Post\">");
            out.println(" <select name=\"code\">");
            for (Order or : orders) {                
                out.println("<option>"+ or.getCode() +"</option>");
            }
            out.println("</select>");
            
            out.println("<br>");
            out.println("<br>");
                      
            out.println("<input type = \"submit\" value = \"Show order\" />");
            out.println("</form>");

        %>


     <div>
        <button onclick="location.href = 'index.jsp'">Back to main</button>
    </div>
</body>
</html>
