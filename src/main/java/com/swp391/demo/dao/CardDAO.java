/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.CardDTO;
import com.swp391.demo.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
public class CardDAO implements Serializable {

    public static CardDAO instance;

    public static CardDAO getInstance() {
        if (instance == null) {
            instance = new CardDAO();
        }
        return instance;
    }

    private Connection con = DBUtil.makeConnection();

    public Boolean CreateCard(CardDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Card(EventId) "
                        + "Values (?)";
                stm = con.prepareStatement(sql);                
                stm.setInt(1, dto.getEventId());
                int x = stm.executeUpdate();
                if (x != 0) {
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

    public Boolean deposite(CardDTO dto) throws SQLException {
        PreparedStatement stm = null;
        double x = getbalance(dto.getId());
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Card "
                        + " Set balance = ? "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, x + dto.getBalance());
                stm.setInt(2, dto.getId());
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

    public double getbalance(int CardId) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        double balance = 0;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select balance "
                        + " From Card "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, CardId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    balance = rs.getDouble("balance");
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

        return balance;

    }

    public Boolean withdraw(CardDTO dto) throws SQLException {
        PreparedStatement stm = null;
        double x = getbalance(dto.getId());
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Card "
                        + " Set balance = ? "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, x - dto.getBalance());
                stm.setInt(2, dto.getId());
                int a = stm.executeUpdate();
                if (a != 0) {
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

    public boolean UpdateCard(CardDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Card "
                        + " Set Username = ?, Balance = ?"
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getName());
                stm.setDouble(2, dto.getBalance());
                stm.setInt(3, dto.getId());
                int x = stm.executeUpdate();
                if (x > 0) {
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
