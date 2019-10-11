<%-- 
    Document   : add
    Created on : 30.09.2019, 0:52:31
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div>
            <form action="addClient" method="post">
                <label>Name:
                    <input type="text" name="name" required placeholder="Client"><br />
                </label>
                <br>
                <label>PhoneNumber:
                    <input type="text" name="phoneNumber" required placeholder="PhoneNumber"><br />
                </label>   
                
                
                <br>
                <button type="submit">Submit</button>
            </form>            
        </div>
        <div>
            <button onclick="location.href = 'index.jsp'">Back to main</button>
        </div>
    </body>
</html>
