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

public class CardDTO implements Serializable {

    private int id;
    private int eventId;
    private String name;
    private double balance;
    private boolean status;
}
