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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lnhtr
 */
public class CardDAO implements Serializable {

    public static CardDAO instance;
    private List<CardDTO> listCard;
    private Connection con = DBUtil.makeConnection();

    public static CardDAO getInstance() {
        if (instance == null) {
            instance = new CardDAO();
        }
        return instance;
    }

    public List<CardDTO> getListCard() {
        return listCard;
    }

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

    public Boolean deposite(int cardId, double balance) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Card "
                        + " Set balance = ? "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, balance);
                stm.setInt(2, cardId);
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

    public double getbalance(int cardId) throws SQLException {
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
                stm.setInt(1, cardId);
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

    public Boolean withdraw(int cardId, double balance) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Card "
                        + " Set balance = ? "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setDouble(1, balance);
                stm.setInt(2, cardId);
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
                        + " Set Username = ?, Balance = ?, PhoneNumber = ?"
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setDouble(2, dto.getBalance());
                stm.setInt(3, dto.getId());
                stm.setString(4, dto.getPhoneNumber());
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

    public CardDTO getInfoCard(int key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        CardDTO result = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + " From Card "
                        + " Where Id = ?";  // and EventId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, key);
//                stm.setInt(2, dto.getEventId());
                rs = stm.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("Id");
                    int eventId = rs.getInt("EventId");
                    String username = rs.getString("Username");
                    double balance = rs.getDouble("Balance");
                    String phoneNumber = rs.getString("PhoneNumber");
                    boolean status = rs.getBoolean("Status");
                    result = new CardDTO(id, eventId, username, balance, phoneNumber, status);
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

    public void getAllCard() throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        listCard = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * "
                        + " From Card ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    int eventId = rs.getInt("EventId");
                    String username = rs.getString("Username");
                    double balance = rs.getDouble("Balance");
                    String phoneNumber = rs.getString("PhoneNumber");
                    boolean status = rs.getBoolean("Status");
                    CardDTO dto = new CardDTO(id, eventId, username, balance, phoneNumber, status);
                    if (listCard == null) {
                        listCard = new ArrayList<>();
                    }
                    listCard.add(dto);
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

    public boolean checkCardUpdate(int key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * from Card "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, key);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("Username");
                    if (username != null) {
                        result = true;
                    }
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
}
