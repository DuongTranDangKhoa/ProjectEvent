/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.EventDTO;
import com.swp391.demo.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lnhtr
 */
public class EventDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();
    private static EventDAO instance;
    private List<EventDTO> listEvent;

    public List<EventDTO> getListEvent() {
        return listEvent;
    }

    public static EventDAO getInstance() {
        if (instance == null) {
            instance = new EventDAO();
        }
        return instance;
    }

    public boolean createEvent(EventDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Event(Name, Desrciption,Image, BeginDate, EndDate, Area, Username)"
                        + " Values(?,?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getDescription());
                stm.setString(3, dto.getImg());
                stm.setDate(4, dto.getBeginDate());
                stm.setDate(5, dto.getEndDate());
                stm.setString(6, dto.getArea());
                stm.setString(7, dto.getUsername());
                int i = stm.executeUpdate();
                if (i > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public void getAllEvent() throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listEvent = null;
        System.out.println("1111111111111111111111111111111111111");
        try {
            con = DBUtil.makeConnection();

            if (con != null) {
                String sql = "Select * From Event";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String name = rs.getString("Name");
                    String description = rs.getString("Desrciption");
                    Date beginDate = rs.getDate("BeginDate");
                    Date endDate = rs.getDate("EndDate");
                    String area = rs.getString("Area");
                    String username = rs.getString("Username");
                    String img = rs.getString("Image");
                    boolean status = rs.getBoolean("Status");
                    EventDTO dto = new EventDTO(id, name, description, beginDate, endDate, area, username, img, status);
                    if (this.listEvent == null) {
                        this.listEvent = new ArrayList<>();
                    }
                    this.listEvent.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public boolean updateEvent(EventDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Event "
                        + " Set Name = ?, Desrciption = ?, BeginDate = ?, EndDate = ?, Status = ?, Image = ?"
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setString(2, dto.getDescription());
                stm.setDate(3, dto.getBeginDate());
                stm.setDate(4, dto.getEndDate());
                stm.setBoolean(5, dto.isStatus());
                stm.setString(6, dto.getImg());
                stm.setInt(7, dto.getId());
                int i = stm.executeUpdate();
                if (i > 0) {
                    result = true;
                }
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
