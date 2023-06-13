/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.OrderCheckDTO;
import com.swp391.demo.dto.OrderDTO;
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
public class OrderDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();
    private static OrderDAO instance;
    private List<OrderDTO> listRevenue;

    public static OrderDAO getInstance() {
        if (instance == null) {
            instance = new OrderDAO();
        }
        return instance;
    }

    public List<OrderDTO> getListRevenue() {
        return listRevenue;
    }

    public int checkOrderId() throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;

        int id = 0;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select Id "
                        + " From [Order] "
                        + " Where Id = ?";
                do {
                    stm = con.prepareStatement(sql);
                    id = (int) (Math.random() * 1000000);
                    stm.setInt(1, id);
                    rs = stm.executeQuery();
                    if (rs.next() == false) {
                        break;
                    }

                } while (true);
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
        return id;
    }

    public boolean createOrder(OrderCheckDTO dto, int id) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into [Order] "
                        + "(Id, Date, ShopId, CardId, Total ) "
                        + "Values (?,CURRENT_TIMESTAMP,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, id);
                stm.setString(2, dto.getShopId());
                stm.setInt(3, dto.getCardId());
                stm.setDouble(4, dto.getTotal());
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

    public void viewRevenue(List<OrderDTO> list) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listRevenue = null;
        String begin = list.get(0).getBeginDate() + " 00:00:00:000";
        String end = list.get(0).getEndDate() + " 23:59:59:999";
        System.out.println(begin);
        System.out.println(end);
        System.out.println(list.get(0).getBeginDate());
        System.out.println(list.get(0).getEndDate());

        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select ShopId, Sum(Total) as Revenue From [Order] "
                        + " Where ShopId = ? "
                        + " and Date  between ? and ? "
                        + " Group by ShopID";
                stm = con.prepareStatement(sql);
                for (int i = 0; i < list.size(); i++) {
                    stm.setString(1, list.get(i).getShopId());
                    stm.setString(2, begin);
                    stm.setString(3, end);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String shopId = rs.getString("ShopId");
                        Double revenue = rs.getDouble("Revenue");
                        OrderDTO result = new OrderDTO(0, shopId, 0, list.get(i).getBeginDate(), list.get(i).getEndDate(), revenue);
                        System.out.println(list.get(0).getBeginDate());
                        System.out.println(list.get(0).getEndDate());
                        if (this.listRevenue == null) {
                            this.listRevenue = new ArrayList<>();
                        }
                        this.listRevenue.add(result);
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

    }
}