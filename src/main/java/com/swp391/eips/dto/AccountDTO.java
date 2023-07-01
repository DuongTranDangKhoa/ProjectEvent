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
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString

public class AccountDTO implements Serializable{
    private String username;
    private String password;
    private String name;
    private String role;
    private boolean status;
}
