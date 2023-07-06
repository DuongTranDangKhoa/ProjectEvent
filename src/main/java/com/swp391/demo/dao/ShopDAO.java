/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.ShopDTO;
import com.swp391.demo.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lnhtr
 */
public class ShopDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();

    private static ShopDAO instance;
    private List<ShopDTO> shopList;

    public List<ShopDTO> getShopList() {
        return shopList;
    }

    public static ShopDAO getInstance() {
        if (instance == null) {
            instance = new ShopDAO();
        }
        return instance;
    }

    public String checkShopExist(ShopDTO dto) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        String result = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select Id "
                        + " From Shop "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getId());
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getString("Id");
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
        return result;
    }

    public void createShop(ShopDTO dto) throws SQLException {
        PreparedStatement stm = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Shop(Id, EventId, Name, Description,Area) "
                        + " Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getId());
                stm.setInt(2, dto.getEventId());
                stm.setNString(3, dto.getName());
                stm.setNString(4, dto.getDes());
                stm.setString(5, dto.getArea());
                stm.execute();
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getShopInEvent(int idEvent) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.shopList = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * From Shop "
                        + " Where EventId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, idEvent);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("Id");
                    int eventId = rs.getInt("EventId");
                    String name = rs.getNString("Name");
                    String description = rs.getNString("Description");
                    String area = rs.getString("Area");
                    Boolean status = rs.getBoolean("Status");
                    ShopDTO dto = new ShopDTO(id, eventId, name, description, area, status);
                    if (this.shopList == null) {
                        this.shopList = new ArrayList<>();
                    }
                    this.shopList.add(dto);
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

    public boolean updateShop(ShopDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Shop"
                        + " Set  EventId = ?, Name = ?, Description = ?, Area = ?, Status = ? "
                        + " Where Id = ?";

                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getEventId());
                stm.setNString(2, dto.getName());
                stm.setNString(3, dto.getDes());
                stm.setNString(4, dto.getArea());
                stm.setBoolean(5, dto.isStatus());
                stm.setString(6, dto.getId());
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

    public ShopDTO getShopByUsername(String key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        ShopDTO dto = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = " Select s.Id, s.EventId, s.Name, s.Description, s.Area, s.Status From Shop s, AccountShop ass "
                        + "    Where s.Id = ass.ShopID "
                        + "    and ass.Username = ? "
                        + "    and ass.Status = 'true'";
                stm = con.prepareStatement(sql);
                stm.setString(1, key);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("Id");
                    int eventId = rs.getInt("EventId");
                    String name = rs.getNString("Name");
                    String des = rs.getNString("Description");
                    String area = rs.getString("Area");
                    Boolean status = rs.getBoolean("Status");
                    dto = new ShopDTO(id, eventId, name, des, area, status);

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
        return dto;
    }
}
