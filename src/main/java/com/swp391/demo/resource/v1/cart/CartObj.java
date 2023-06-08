/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.cart;

import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author lnhtr
 */
public class CartObj implements Serializable{
    
    private Map<Integer, Integer> items;

    public Map<Integer, Integer> getItems() {
        return items;
    }
    
    
}
