<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>this is the JSP view</title>
</head>
<body>
        <% out.print(request.getAttribute("text"));
        %>
</body>
</html>
