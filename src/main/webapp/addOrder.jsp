<%-- 
    Document   : add
    Created on : 30.09.2019, 0:52:31
    Author     : Lenovo
--%>

<%@page import="com.store.entities.Goods"%>
<%@page import="com.store.entities.Client"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            List<Client> clients = (List<Client>) request.getAttribute("clients");
            List<Goods> goods = (List<Goods>) request.getAttribute("goods");
            out.println("<form action=\"addOrder\" method=\"Post\">");
            out.println(" <select name=\"client\">");
            for (Client c : clients) {
                out.println("<option>" + c.getName() + "</option>");
            }
            out.println("</select>");
            out.println(" <select name=\"goods\">");
            out.println("<br>");
            out.println("<table  border = \"1\">");
            out.println("<tr>"
                    + "<th> Goods </th>"
                    + "<th>Articul</th> "
                    + "<th>Select </th> "
                    + "</tr> ");
            if (!goods.isEmpty()) {
                for (Goods g : goods) {
                    out.println("<tr>");
                    out.println("<td>" + g.getName() + "</td>");
                    out.println("<td>" + g.getArticul() + "</td>");
                    out.println("<td> <input type=\"checkbox\" name=\"goods\" value=\"" + g.getId() + "\"" + "</td>");
                    out.println("</tr>");
                }
            } else {
                out.println("<h1>" + "Sorry, we don't have any information yet" + "</h1>");
            }
            out.println("<input type = \"submit\" value = \"Delete marked\" />");
            out.println("</form>");

        %>


    </div>
    <div>
        <button onclick="location.href = 'index.jsp'">Back to main</button>
    </div>
</body>
</html>
