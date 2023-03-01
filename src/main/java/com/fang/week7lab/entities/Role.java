/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fang.week7lab.entities;

import java.io.Serializable;

/**
 *
 * @author jifang
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
	private long id;
    private String name;
    
    public Role() {
    }
    
    public Role(long id, String name) {
    	this.id = id;
        this.name = name;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
}
