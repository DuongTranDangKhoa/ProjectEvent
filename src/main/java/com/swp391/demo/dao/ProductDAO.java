/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.AccountShopDTO;
import com.swp391.demo.dto.ProductDTO;
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
public class ProductDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();
    private List<ProductDTO> productAllList;
    private List<ProductDTO> productSaleList;

    private static ProductDAO instance;

    public List<ProductDTO> getAllProductList() {
        return productAllList;
    }

    public List<ProductDTO> getProductSaleList() {
        return productSaleList;
    }

    public static ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    public void getAllProductShop(AccountShopDTO dto) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.productAllList = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "select p.Id, p.ShopId, p.Name, p.Price, p.Image, p.Description, p.CategoryId, p.Status "
                        + "     from Product p, [AccountShop] sa "
                        + "	where p.ShopId = sa.ShopId "
                        + "	and sa.Username = ? and sa.Status = 'true'";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("Id");
                    String shopId = rs.getString("ShopId");
                    String name = rs.getString("Name");
                    Double price = rs.getDouble("Price");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    Integer categoryId = rs.getInt("CategoryId");
                    boolean status = rs.getBoolean("Status");

                    ProductDTO x = new ProductDTO(id, shopId, name, price, img, description, categoryId, status);
                    if (this.productAllList == null) {
                        this.productAllList = new ArrayList<>();
                    }
                    this.productAllList.add(x);

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

    public void getSaleProductShop(AccountShopDTO dto) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.productSaleList = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "select p.Id, p.ShopId, p.Name, p.Price, p.Image, p.Discription, p.CategoryId "
                        + "     from Product p, [ShopAccount] sa "
                        + "	where p.ShopId = sa.ShopId "
                        + "	and sa.Username = ? and sa.Status = 'false'";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("Id");
                    String shopId = rs.getString("ShopId");
                    String name = rs.getString("Name");
                    Double price = rs.getDouble("Price");
                    String img = rs.getString("Image");
                    String content = rs.getString("Discription");
                    Integer categoryId = rs.getInt("CategoryId");
                    boolean status = rs.getBoolean("Status");

                    ProductDTO x = new ProductDTO(id, shopId, name, price, img, content, categoryId, status);
                    if (this.productSaleList == null) {
                        this.productSaleList = new ArrayList<>();
                    }
                    this.productSaleList.add(x);

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

    public boolean setProductStatus(ProductDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Product "
                        + " Set Status = ?"
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, dto.isStatus());
                stm.setInt(2, dto.getId());
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

    public boolean creatProduct(ProductDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Product (ShopId, Name, Price, Image, Description, categoryId) "
                        + "Values (?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getShopId());
                stm.setString(2, dto.getName());
                stm.setDouble(3, dto.getPrice());
                stm.setString(4, dto.getImg());
                stm.setString(5, dto.getDescription());
                stm.setInt(6, dto.getCategoryId());
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

    public int getIdProduct(ProductDTO dto) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select Id From Product "
                        + " Where Image = ?";
                stm = con.prepareStatement(sql);

                stm.setString(1, dto.getImg());
                rs = stm.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("Id");
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