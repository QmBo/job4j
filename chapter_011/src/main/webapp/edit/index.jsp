<%@ page import="ru.job4j.servlets.userservlet.User" %>
<%@ page import="ru.job4j.servlets.userservlet.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<% User user = ValidateService.getInstance().findById(request);%>
<%if (user != null) {%>
<form action="<%=request.getContextPath()%>/edit" method="post">
    <input type="hidden" name="id" value="<%=user.getId()%>">
    <table>
        <tr>
            <td>User name: </td>
            <td><input type="text" name="name" value="<%=user.getName()%>" title="User name"></td>
        </tr>
        <tr>
            <td>User login: </td>
            <td><input type="text" name="login" value="<%=user.getLogin()%>" title="User login"></td>
        </tr>
        <tr>
            <td>User email: </td>
            <td><input type="email" name="email" value="<%=user.getEmail()%>" title="User email"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update"></td>
        </tr>
    </table>
</form>
<%} else {%>
<h2>Users not found</h2>
<%}%>
<form action="<%=request.getContextPath()%>/">
    <button>Users List</button>
</form>
</body>
</html>
