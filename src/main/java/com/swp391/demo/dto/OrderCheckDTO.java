/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author lnhtr
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderCheckDTO implements Serializable{
    
    private String shopId;      //order
    private int cardId;         //order
    private double total;       //order
    
    private int productId;      //orderDetail
    private int quantity;       //orderDetail
    private double price;
    
    //sua thanh String khi lam xong
    private String category;     //orderDetail
}
