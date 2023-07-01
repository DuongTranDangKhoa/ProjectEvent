/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.dao;

import com.swp391.eips.dto.AccountShopDTO;
import com.swp391.eips.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public boolean setStatus(AccountShopDTO dto) throws SQLException {
        PreparedStatement stm = null;
        Boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update AccountShop "
                        + " Set Status = ? "
                        + "  Where Username = ? And ShopId = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, dto.isStatus());
                stm.setString(2, dto.getUsername());
                stm.setString(3, dto.getShopId());
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

    public boolean checkStatusExist(String username) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * From AccountShop "
                        + " Where Username = ? and Status  = 'true'";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
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
