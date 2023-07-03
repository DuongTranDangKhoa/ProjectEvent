/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.OrderCheckDTO;
import com.swp391.demo.dto.OrderDetailDTO;
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
public class OrderDetailDAO implements Serializable {
    
    private Connection con = DBUtil.makeConnection();
    private static OrderDetailDAO instance;
    private List<OrderDetailDTO> listProductSold;

    public List<OrderDetailDTO> getListProductSold() {
        return listProductSold;
    }
    
    
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
    
    public void viewQuatityProduct(String key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        listProductSold = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select od.ProductId, SUM(od.Quantity) as Quantity from [Order] o, OrderDetail od \n"
                        + " where o.Id = od.OrderId"
                        + " and o.ShopId = ? "
                        + " Group by od.ProductId";
                stm = con.prepareStatement(sql);
                stm.setString(1, key);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int productId = rs.getInt("ProductId");
                    int quantity = rs.getInt("Quantity");
                    OrderDetailDTO dto = new  OrderDetailDTO(0, productId, quantity, 0, 0);
                    if (listProductSold == null) {
                        listProductSold = new ArrayList<>();
                    }
                    listProductSold.add(dto);
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
}
