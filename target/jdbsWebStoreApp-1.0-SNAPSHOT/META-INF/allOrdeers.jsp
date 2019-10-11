<%-- 
    Document   : allOrdeers
    Created on : 10.10.2019, 21:52:30
    Author     : Lenovo
--%>

<%@page import="com.store.entities.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%-- 
     Document   : add
     Created on : 30.09.2019, 0:52:31
     Author     : Lenovo
        --%>

       
        <%@page import="java.util.List"%>
        
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Orders</title>
        </head>
        <body>

            <%
                List<Order> orders = (List<Order>) request.getAttribute("orders");
                out.println("<form action=\"getOneorder\" method=\"Post\">");
                out.println(" <select name=\"order\">");
                if (!orders.isEmpty()) {
                    for (Order or : orders) {
                        out.println("<option>" + or.getCode() + "</option>");
                    }
                    out.println("</select>");

                } else {
                    out.println("<h1>" + "Sorry, we don't have any information yet" + "</h1>");
                }
                out.println("<input type = \"submit\" value = \"Show order\" />");
                out.println("</form>");

            %>


        </div>
        <div>
            <button onclick="location.href = 'index.jsp'">Back to main</button>
        </div>
    </body>
</html>

</body>
</html>
