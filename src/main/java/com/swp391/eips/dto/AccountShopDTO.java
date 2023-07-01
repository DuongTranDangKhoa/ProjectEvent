/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.dto;

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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AccountShopDTO implements Serializable{
    
    private String username;
    private String shopId;
    private boolean status;
    
}
