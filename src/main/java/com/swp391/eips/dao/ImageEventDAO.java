/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.dao;

import com.swp391.eips.dto.ImageEventDTO;
import com.swp391.eips.util.DBUtil;
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
public class ImageEventDAO implements Serializable {

    private static ImageEventDAO instance;
    private List<ImageEventDTO> imageList;
    private Connection con = DBUtil.makeConnection();

    public static ImageEventDAO getInstance() {
        if (instance == null) {
            instance = new ImageEventDAO();
        }
        return instance;
    }

    public List<ImageEventDTO> getImageList() {
        return imageList;
    }

    public void listImage(int key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        imageList = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * From ImageEvent "
                        + " Where EventId = ?";
                stm = con.prepareStatement(sql);
                stm.setInt(1, key);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int no = rs.getInt("No");
                    String image = rs.getString("Image");
                    ImageEventDTO dto = new ImageEventDTO(no, key, image);
                    if (imageList == null) {
                        imageList = new ArrayList<>();
                    }
                    imageList.add(dto);
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

    public boolean addImage(ImageEventDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into ImageEvent(EventId, Image) "
                        + " Values (?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, dto.getEventId());
                stm.setString(2, dto.getImg());
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
