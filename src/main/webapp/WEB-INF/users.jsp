<%-- 
    Document   : users
    Created on : Feb 20, 2023, 7:31:21 PM
    Author     : jifang
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management</title>
    </head>
    <body>
        <h1>Manage Users</h1>
        <c:choose>
        <c:when test="${users.size() > 0}">
        <table border="2">
            <tr>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Role</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.role.name}</td>
                    <td><a href=
                            <c:url value="users">
                            <c:param name="action" value="edit" />
                            <c:param name="email" value="${user.email}" />
                            </c:url>
                        >Edit</a></td>
                    <td><a href=
                            <c:url value="users">
                            <c:param name="action" value="delete" />
                            <c:param name="email" value="${user.email}" />
                            </c:url>
                        >Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
            <h3>No users found. Please add a user.</h3>
        </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${action == 'view'}">
            <form action=<c:url value="users"/> method="post">
                <h1>Add User</h1>
                Email: <input type="text" name="email"/><br>
                First name: <input type="text" name="firstname"/><br>
                Last name: <input type="text" name="lastname"/><br>
                Password: <input type="password" name="password"/><br>
                Role:
                <select name="role">
                    <c:forEach var="role" items="${roles}">
                    <option value="${role.id}">${role.name}
                    </option>
                    </c:forEach>
                </select><br>
                <input type="hidden" name="action" value="adduser"/>  
                <input type="submit" value="Add user"/><br>
            </form>
            </c:when>
            <c:when test="${action == 'edit'}">
            <form action=<c:url value="users"/> method="post">
                <h1>Edit User</h1>
                Email: <input type="text" name="email" value="${user.email}" readonly /><br>
                First name: <input type="text" name="firstname" value="${user.firstName}"/><br>
                Last name: <input type="text" name="lastname" value="${user.lastName}"/><br>
                Password: <input type="password" name="password" value=""/><br>
                Role:
					<select name="role">
						<c:forEach var="role" items="${roles}">
						<option value="${role.id}"
						    <c:if test="${user.role.name eq role.name}">
	                            selected
	                        </c:if>>
	                        ${role.name}
						</option>
						</c:forEach>
					</select><br>
				<input type="hidden" name="action" value="updateuser" />
                <input type="submit" value="Update"/>
                
                <a href=<c:url value="users">
                            <c:param name="action" value="view" />
                        </c:url>
                ><input type="button" value="Cancel" /></a><br>
            </form>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose><br>
        ${message}
    </body>
</html>
