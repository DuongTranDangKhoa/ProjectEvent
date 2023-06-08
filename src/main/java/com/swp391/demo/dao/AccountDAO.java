/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.AccountDTO;
import com.swp391.demo.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
public class AccountDAO {

    private static AccountDAO instance;

    public static AccountDAO getInstance() {

        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    private Connection con = DBUtil.makeConnection();

    public AccountDTO CheckLogin(AccountDTO account) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO dto = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * from account "
                        + "Where username = ? and password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    String role = rs.getString("role");
                    Boolean status = rs.getBoolean("status");
                    dto = new AccountDTO(account.getUsername(), account.getPassword(), name, role, status);
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

    public void createAccount(AccountDTO dto) throws SQLException {
        PreparedStatement stm = null;
        
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Account(username, password, name, role) "
                        + " values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getName());
                stm.setString(4, dto.getRole());
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
}
