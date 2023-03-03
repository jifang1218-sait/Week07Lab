package com.fang.week7lab.services;

import java.util.List;

import com.fang.week7lab.dataaccess.DBUtil;
import com.fang.week7lab.entities.Role;
import com.fang.week7lab.entities.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserService {
	
	private static class Holder {
        private static final UserService _instance = new UserService();
    }
    
    
    private UserService() {
    }
    
    public static UserService instance() {
        return Holder._instance;
    }
    
    public List<User> getUsers(HttpServletRequest req, HttpServletResponse resp) {
    	List<User> ret = DBUtil.getUsers();
        if (ret != null && req != null) {
            req.setAttribute("users", ret);
        }
        
        return ret;
    }
    
    public User getUserByEmail(String email, 
            HttpServletRequest req, HttpServletResponse resp) {
        User ret = DBUtil.getUserByEmail(email);
        
        if (ret != null && req != null) {
            req.setAttribute("user", ret);
        }
        
        return ret;
    }
    
    public User removeUserByEmail(String email, 
            HttpServletRequest req, HttpServletResponse resp) {
        User ret = DBUtil.getUserByEmail(email);
        if (ret != null) {
            DBUtil.deleteUser(email);
            // dataset changed, need to refresh.
            // pass action, users, roles to jsp
            List<User> users = DBUtil.getUsers();
            List<Role> roles = DBUtil.getRoles();
            req.setAttribute("users", users);
            req.setAttribute("roles", roles);
            req.setAttribute("user", ret);
        }
        
        return ret;
    }
    
    public User addUser(User user, 
            HttpServletRequest req, 
            HttpServletResponse resp) {
        User ret = null;
        
        if (user != null)  {
            ret = DBUtil.addUser(user);
        }
        
        if (ret != null) {
            req.setAttribute("user", ret);
        }
        
        return ret;
    }
    
    public User updateUser(User user, 
            HttpServletRequest req, 
            HttpServletResponse resp) {
        User ret = null;
        
        if (user != null) {
            ret = DBUtil.updateUser(user);
        }
        
        if (ret != null) {
            req.setAttribute("user", ret);
        }
        
        return ret;
    }
}
