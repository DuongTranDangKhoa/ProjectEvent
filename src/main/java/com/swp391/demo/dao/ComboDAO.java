/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.ComboDTO;
import com.swp391.demo.util.DBUtil;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lnhtr
 */
public class ComboDAO implements Serializable {

    private static ComboDAO instance;
    private Connection con = DBUtil.makeConnection();

    public static ComboDAO getInstance() {
        if (instance == null) {
            instance = new ComboDAO();
        }
        return instance;
    }

    public boolean addComboProduct(List<ComboDTO> list, int id) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Combo (Id, IdMake, Quantity) "
                        + "Values (?,?,?)";
                stm = con.prepareStatement(sql);
                for (ComboDTO x : list) {
                    stm.setInt(1, id);
                    stm.setInt(2, x.getIdmake());
                    stm.setInt(3, x.getQuatity());
                    int i = stm.executeUpdate();
                    if (i > 0) {
                        result = true;
                    }
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
