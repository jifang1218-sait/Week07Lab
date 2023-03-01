package com.fang.week7lab.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fang.week7lab.dataaccess.DBUtil;
import com.fang.week7lab.entities.Role;

public class RoleService {
	
	private Map<Long, Role> roles;
	
	private static class Holder {
        private static final RoleService _instance = new RoleService();
    }
    
    
    private RoleService() {
    	roles = new HashMap<Long, Role>();
    	List<Role> rs = DBUtil.getRoles();
    	for (int i=0; i<rs.size(); ++i) {
    		Role r = rs.get(i);
    		roles.put(r.getId(), r);
    	}
    }
    
    public static RoleService instance() {
        return Holder._instance;
    }
    
    public String getNameById(long id) {
    	Role r = roles.get(id);
    	return r.getName();
    }
    
    public List<Role> getRoles() {
    	return DBUtil.getRoles();
    }
}
