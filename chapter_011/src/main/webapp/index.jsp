<%@ page import="ru.job4j.servlets.userservlet.ValidateService" %>
<%@ page import="ru.job4j.servlets.userservlet.User" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Users List</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/create/">
    <button>Create user</button>
</form>
<%Set<User> users = ValidateService.getInstance().findAll();%>
<%if (!users.isEmpty()) {%>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>Create date</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <%for (User user: users) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/edit/" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <input type="submit" value="Update">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/list" method="post">
                <input type="hidden" name="del" value="<%=user.getId()%>">
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <%}%>
</table>
<%} else {%>
<h2>Users not found</h2>
<%}%>
</body>
</html>
