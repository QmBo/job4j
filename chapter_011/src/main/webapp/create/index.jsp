<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>

<form action="<%=request.getContextPath()%>/create" method="post">
    <table>
        <tr>
            <td>User name: </td>
            <td><input type="text" name="name" placeholder="User name"></td>
        </tr>
        <tr>
            <td>User login: </td>
            <td><input type="text" name="login" placeholder="User login"></td>
        </tr>
        <tr>
            <td>User email: </td>
            <td><input type="email" name="email" placeholder="User email"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Create"></td>
        </tr>
    </table>
</form>
</body>
</html>
