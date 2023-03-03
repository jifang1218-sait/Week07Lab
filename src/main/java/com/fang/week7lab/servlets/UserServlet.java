/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.fang.week7lab.servlets;

import com.fang.week7lab.entities.Role;
import com.fang.week7lab.entities.User;
import com.fang.week7lab.services.RoleService;
import com.fang.week7lab.services.UserService;
import com.fang.week7lab.utils.SDLogger;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jifang
 */
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }
        String message = "";
        String path = "/WEB-INF/users.jsp";
        if (action.equalsIgnoreCase("view")) {
            // pass action, users, roles to jsp
            UserService.instance().getUsers(request, response);
            RoleService.instance().getRoles(request, response);
            request.setAttribute("action", action);
        } else if (action.equalsIgnoreCase("edit")) {
            // pass action, users, roles, currentUser to jsp
            String email = request.getParameter("email");
            UserService.instance().getUserByEmail(email, request, response);
            UserService.instance().getUsers(request, response);
            RoleService.instance().getRoles(request, response);
            request.setAttribute("action", "edit");
        } else if (action.equalsIgnoreCase("delete")) {
            String email = request.getParameter("email");
            User user = UserService.instance().removeUserByEmail(email, request, response);
            if (user != null) {
                message = "user: " + email + " deleted.";
            } else {
                SDLogger.warn("user: " + email + " doesn't exist.");
                message = "user: " + email + " doesn't exist.";
            }
            action = "view";
            request.setAttribute("action", action);
        } else {
            SDLogger.warn("Unknown action: " + action);
            message = "Unknown action: " + action;
        }
        request.setAttribute("message", message);
        getServletContext()
	        .getRequestDispatcher(path)
	        .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String email = request.getParameter("email").trim();
        String firstName = request.getParameter("firstname").trim();
        String lastName = request.getParameter("lastname").trim();
        String password = request.getParameter("password").trim();
        String strRoleId = request.getParameter("role").trim();
    	String action = request.getParameter("action").trim();
    	String message = "";
    	if (action.equalsIgnoreCase("adduser")) {
            // validate input
            if ((email.length() == 0) 
                    || (firstName.length() == 0)
                    || (lastName.length() == 0) 
                    || (password.length() == 0)) {
                message = "All fields are required.";
            } else { // add user
                if (UserService.instance().getUserByEmail(email, 
                        request, response) == null) {
                    User user = new User();
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPassword(password);
                    Role role = new Role();
                    role.setId(Long.parseLong(strRoleId));
                    RoleService roleMgr = RoleService.instance();
                    role.setName(roleMgr.getNameById(role.getId()));
                    user.setRole(role);
                    UserService.instance().addUser(user, request, response);
                    message = "user email: " + email + " (" + user.getFirstName() + " " + user.getLastName() + ") added.";
                } else {
    			SDLogger.warn("user email: " + email + " already exists.");
    			message = "user email: " + email + " already exists.";
    		}
            } 
        }else if (action.equalsIgnoreCase("updateuser")) {
            // validate input
            if ((email.length() == 0) || (firstName.length() == 0)
                    || (lastName.length() == 0)) {
                message = "All fields except password are required.";
            } else {
                User user = UserService.instance().getUserByEmail(email, 
                        request, response); 
                if (user != null) {
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    if (password.length() > 0) {
                            user.setPassword(password);
                    }
                    Role role = new Role();
                    role.setId(Long.parseLong(strRoleId));
                    RoleService roleMgr = RoleService.instance();
                    role.setName(roleMgr.getNameById(role.getId()));
                    user.setRole(role);
                    UserService.instance().updateUser(user, 
                            request, response);
                    message = "user email: " + email + " (" + user.getFirstName() + " " + user.getLastName() + ") updated.";
    		} else {
                    SDLogger.warn("user email: " + email + " doesn't exist.");
                    message = "user email: " + email + " doesn't exist.";
    		}
            } 
        } else {
            SDLogger.warn("Unknown action: " + action);
    	}
    	
    	// dataset changed, refresh ui.
    	action = "view";
    	String path = "/WEB-INF/users.jsp";
        UserService.instance().getUsers(request, response);
        RoleService.instance().getRoles(request, response);
        request.setAttribute("action", action);
        request.setAttribute("message", message);
        getServletContext()
	        .getRequestDispatcher(path)
	        .forward(request, response);
    }

}
