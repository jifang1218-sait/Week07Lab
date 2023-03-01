package com.fang.week7lab.services;

import java.util.List;

import com.fang.week7lab.dataaccess.DBUtil;
import com.fang.week7lab.entities.Role;
import com.fang.week7lab.entities.User;
import com.fang.week7lab.utils.SDLogger;

public class UserService {
	
	private static class Holder {
        private static final UserService _instance = new UserService();
    }
    
    
    private UserService() {
    }
    
    public static UserService instance() {
        return Holder._instance;
    }
    
    public List<User> getUsers() {
    	return DBUtil.getUsers();
    }
}
