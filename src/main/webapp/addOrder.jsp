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

        <div>           
            <%
                List<Client> clients =(List<Client>) request.getAttribute("clients");
                List<Goods> goods = (List<Goods>) request.getAttribute("goods");
                out.println("<form action=\"addOrder\" method=\"Post\">");
                out.println(" <select name=\"client\">");
                
                   
                       
                            <option>price</option>
                            <option>square</option>
                            <option>room</option>
                        </select>
                            </form>

 

            %>
                <form action="selectByParam" method="Post"  >
                    <label>By parameter:
                        <select name="param">
                            <option>price</option>
                            <option>square</option>
                            <option>room</option>
                        </select>
                    </label>
                    <label>Min value:
                        <input type="text" name="min" required placeholder="Min value"><br />
                    </label>
                    <label>Max value:
                        <input type="text" name="max" required placeholder="Max value"><br />
                    </label>

                    <br>
                    <button type="submit">Select</button>
                    <br>
                    <br>    
                </form>>



        </div>
        <div>
            <button onclick="location.href = 'index.jsp'">Back to main</button>
        </div>
    </body>
</html>
