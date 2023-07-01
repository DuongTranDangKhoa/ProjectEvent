/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.dao;

import com.swp391.eips.dto.OrderCheckDTO;
import com.swp391.eips.dto.OrderDetailDTO;
import com.swp391.eips.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
public class OrderDetailDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();
    private static OrderDetailDAO instance;

    public static OrderDetailDAO getInstance() {
        if (instance == null) {
            instance = new OrderDetailDAO();
        }
        return instance;
    }

    public boolean createDetail(OrderDetailDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into OrderDetail "
                        + " (OrderId, ProductId, Quantity, Price, Total)"
                        + " Values(?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getOrderId());
                stm.setInt(2, dto.getProductId());
                stm.setInt(3, dto.getQuantity());
                stm.setDouble(4, dto.getPrice());
                stm.setDouble(5, dto.getPrice() * dto.getQuantity());
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
