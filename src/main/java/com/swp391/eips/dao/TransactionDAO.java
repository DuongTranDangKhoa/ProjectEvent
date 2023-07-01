/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.dao;

import com.swp391.eips.dto.TransactionDTO;
import com.swp391.eips.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
public class TransactionDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();

    private static TransactionDAO instance;

    public static TransactionDAO getInstance() {
        if (instance == null) {
            instance = new TransactionDAO();
        }
        return instance;
    }

    public boolean createTransaction(TransactionDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into [Transaction] (CardId, OrderId) "
                        + " values(?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getCardId());
                stm.setInt(2, dto.getOrderId());
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
