package com.fang.week7lab.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fang.week7lab.dataaccess.DBUtil;
import com.fang.week7lab.entities.Role;
import java.util.LinkedList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RoleService {
	
	private Map<Long, Role> roles;
	
	private static class Holder {
        private static final RoleService _instance = new RoleService();
    }
    
    private RoleService() {
    	reload();
    }
    
    public static RoleService instance() {
        return Holder._instance;
    }
    
    public String getNameById(long id) {
    	Role r = roles.get(id);
    	return r.getName();
    }
    
    public List<Role> getRoles(HttpServletRequest req, HttpServletResponse resp) {
        List<Role> ret = new LinkedList<>(roles.values());
        req.setAttribute("roles", ret);
        
        return ret;
    }
    
    public final void reload() {
        roles = new HashMap<>();
    	List<Role> rs = DBUtil.getRoles();
    	for (int i=0; i<rs.size(); ++i) {
    		Role r = rs.get(i);
    		roles.put(r.getId(), r);
    	}
    }
}
