/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.AccountDTO;
import com.swp391.demo.dto.AccountSetDTO;
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
public class AccountSetDAO implements Serializable {

    private static AccountSetDAO instance;
    private Connection con = DBUtil.makeConnection();

    public static AccountSetDAO getInstance() {

        if (instance == null) {
            instance = new AccountSetDAO();
        }
        return instance;
    }

    public void createAccount(AccountSetDTO dto) throws SQLException {
        PreparedStatement stm = null;
        
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Account(Username, Password, Name, Role) "
                        + " values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setNString(3, dto.getName());
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
    
    public void setAcccountShop(AccountSetDTO dto) throws SQLException{
       PreparedStatement stm = null;
        
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into AccountShop(Username, ShopId) "
                        + " values(?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getShopId());
               
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
    
    public boolean checkExistAccount(AccountSetDTO dto) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * from account "
                        + "Where username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());               
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
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
