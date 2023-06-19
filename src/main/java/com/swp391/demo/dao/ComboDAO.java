/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.ComboDTO;
import com.swp391.demo.dto.ProductComboDTO;
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
public class ComboDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();
    private List<ComboDTO> listCombo;
    private static ComboDAO instance;

    public List<ComboDTO> getListCombo() {
        return listCombo;
    }

    public static ComboDAO getInstance() {
        if (instance == null) {
            instance = new ComboDAO();
        }
        return instance;
    }

    public boolean addComboProduct(ProductComboDTO dto, int id) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Combo (Id, IdMake, Quantity) "
                        + "Values (?,?,?)";
                stm = con.prepareStatement(sql);

                stm.setInt(1, id);
                stm.setInt(2, dto.getIdMake());
                stm.setInt(3, dto.getQuantity());
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

    public void getInfoCombo(int key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listCombo = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select No, Id, IdMake, Quantity "
                        + " From Combo"
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, key);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int no = rs.getInt("No");
                    int id = rs.getInt("Id");
                    int idMake = rs.getInt("IdMake");
                    int quantity = rs.getInt("Quantity");
                    ComboDTO dto = new ComboDTO(no, id, idMake, quantity);
                    if (this.listCombo == null) {
                        this.listCombo = new ArrayList<>();
                    }
                    this.listCombo.add(dto);
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
