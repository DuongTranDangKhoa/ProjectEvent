/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.dao;

import com.swp391.eips.dto.AccountShopDTO;
import com.swp391.eips.dto.ProductComboDTO;
import com.swp391.eips.dto.ProductDTO;
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
public class ProductDAO implements Serializable {

    private Connection con = DBUtil.makeConnection();
    private List<ProductDTO> productAllList;
    private List<ProductDTO> productSaleList;
    private List<ProductDTO> productList;
    

    private static ProductDAO instance;

    public List<ProductDTO> getProductList() {
        return productList;
    }

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

    public void getAllProductShop(String key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.productAllList = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = " Select Id, shopId, Name, Price, Image, Description, Category, Status "
                        + " From Product "
                        + " Where ShopId = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, key);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("Id");
                    String shopId = rs.getString("ShopId");
                    String name = rs.getString("Name");
                    Double price = rs.getDouble("Price");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    String category = rs.getString("Category");
                    boolean status = rs.getBoolean("Status");

                    ProductDTO x = new ProductDTO(id, shopId, name, price, img, description, category, status);
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

    public void getSaleProductShop(String key) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.productSaleList = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select Id, ShopId, Name, Price, Image, Description, Category, Status "
                        + "     From Product "
                        + "	Where ShopId = ? and Status = 'true'";
                stm = con.prepareStatement(sql);
                stm.setString(1, key);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("Id");
                    String shopId = rs.getString("ShopId");
                    String name = rs.getString("Name");
                    Double price = rs.getDouble("Price");
                    String img = rs.getString("Image");
                    String content = rs.getString("Description");
                    String category = rs.getString("Category");
                    boolean status = rs.getBoolean("Status");

                    ProductDTO x = new ProductDTO(id, shopId, name, price, img, content, category, status);
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

    public boolean updateProduct(ProductDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Update Product "
                        + " Set Status = ?, Name = ?, Price = ?, Image = ?, Description = ? "
                        + " Where Id = ?";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, dto.isStatus());
                stm.setString(2, dto.getName());
                stm.setDouble(3, dto.getPrice());
                stm.setString(4, dto.getImg());
                stm.setString(5, dto.getDescription());
                stm.setInt(6, dto.getId());
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
                String sql = "Insert into Product (ShopId, Name, Price, Image, Description, Category) "
                        + "Values (?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getShopId());
                stm.setString(2, dto.getName());
                stm.setDouble(3, dto.getPrice());
                stm.setString(4, dto.getImg());
                stm.setString(5, dto.getDescription());
                stm.setString(6, dto.getCategory());
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

    public int getIdProduct(ProductComboDTO dto) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select Id From Product "
                        + " Where Image = ?  and ShopId = ? and [Description] = ? and name =?";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getImg());
                stm.setString(2, dto.getShopId());
                stm.setString(3, dto.getDescription());
                stm.setString(4, dto.getName());
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
    
       public boolean creatProduct(ProductComboDTO dto) throws SQLException {
        PreparedStatement stm = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Product (ShopId, Name, Price, Image, Description, Category) "
                        + "Values (?,?,?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getShopId());
                stm.setString(2, dto.getName());
                stm.setDouble(3, dto.getPrice());
                stm.setString(4, dto.getImg());
                stm.setString(5, dto.getDescription());
                stm.setString(6, dto.getCategory());
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
       
        public void getProductShop(String idShop) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.productList = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select Id, ShopId, Name, Price, Image, Description, CategoryId, Status "
                        + "     from Product  "
                        + "	where  ShopId = ?";
                       	
                stm = con.prepareStatement(sql);
                stm.setString(1, idShop);
                rs = stm.executeQuery();
                while (rs.next()) {
                    Integer id = rs.getInt("Id");
                    String shopId = rs.getString("ShopId");
                    String name = rs.getString("Name");
                    Double price = rs.getDouble("Price");
                    String img = rs.getString("Image");
                    String description = rs.getString("Description");
                    String category = rs.getString("Category");
                    boolean status = rs.getBoolean("Status");

                    ProductDTO x = new ProductDTO(id, shopId, name, price, img, description, category, status);
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
        
     public double getPrice(int key) throws SQLException{
         PreparedStatement stm = null;
         ResultSet rs = null;
         double price = 0;
         try {
             con = DBUtil.makeConnection();
             if (con != null) {
                 String sql ="Select Price "
                         + " From Product "
                         + " Where Id = ?";
                 stm = con.prepareStatement(sql);
                 stm.setInt(1, key);
                 rs = stm.executeQuery();
                 if (rs.next()) {
                     price = rs.getDouble("Price");
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
         return price;
     }   
}
