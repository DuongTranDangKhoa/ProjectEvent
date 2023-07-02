/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dto;

import java.io.Serializable;
import java.sql.Date;
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
public class EventDTO implements Serializable{
    private int id;
    private String name;
    private String description;
    private Date beginDate;
    private Date endDate;
    private String area;
    private String username;
    private String img;
    private boolean status;
}
