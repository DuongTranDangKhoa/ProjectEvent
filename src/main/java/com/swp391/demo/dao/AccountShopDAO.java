/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.AccountShopDTO;
import com.swp391.demo.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
public class AccountShopDAO implements Serializable {

    private static AccountShopDAO instance;
    private Connection con = DBUtil.makeConnection();

    public static AccountShopDAO getInstance() {
        if (instance == null) {
            instance = new AccountShopDAO();
        }
        return instance;
    }

    public boolean setAccountShop(AccountShopDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into AccountShop(Username, ShopId) "
                        + " Values(?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getShopId());
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
    
    public boolean setStatus(AccountShopDTO dto) throws SQLException{
        PreparedStatement stm = null;
        Boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update AccountShop "
                        + "Set Status = 'false' "
                        + "Where Username = ? and ShopId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getShopId());
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
