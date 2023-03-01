/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fang.week7lab.dataaccess;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author jifang
 */
public class ConnectionPool {
    private static class Holder {
        private static final ConnectionPool _instance = new ConnectionPool();
    }
    
    private DataSource _datasource;
    
    private ConnectionPool() {
        this._datasource = null;
        try {
            InitialContext ic = new InitialContext();
            _datasource = (DataSource)ic.lookup("java:/comp/env/jdbc/week7");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    
    public static ConnectionPool instance() {
        return Holder._instance;
    }
    
    public Connection getConnection() {
        Connection ret = null;
        
        try {
            ret = _datasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ret;
    }
    
    public void closeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
