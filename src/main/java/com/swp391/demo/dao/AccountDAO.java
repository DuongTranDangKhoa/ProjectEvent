/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.dao;

import com.swp391.demo.dto.AccountDTO;
import com.swp391.demo.util.DBUtil;
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
public class AccountDAO {

    private static AccountDAO instance;
    private List<AccountDTO> listAccount;

    public static AccountDAO getInstance() {

        if (instance == null) {
            instance = new AccountDAO();
        }
        return instance;
    }

    public List<AccountDTO> getListAccount() {
        return listAccount;
    }
    
    

    private Connection con = DBUtil.makeConnection();

    public AccountDTO checkLogin(AccountDTO account) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        AccountDTO dto = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * from Account "
                        + "Where Username = ? and Password = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                rs = stm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("Name");
                    String role = rs.getString("Role");
                    Boolean status = rs.getBoolean("Status");
                    dto = new AccountDTO(account.getUsername(), account.getPassword(), name, role, status);
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
        return dto;
    }

    public void createAccount(AccountDTO dto) throws SQLException {
        PreparedStatement stm = null;
        
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Insert into Account(Username, Password, Name, Role) "
                        + " values(?,?,?,?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getName());
                stm.setString(4, dto.getRole());
                stm.execute();
            }
        } finally {
            
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }
    
    public void getAllListAccount() throws SQLException{
        PreparedStatement stm = null;
        ResultSet rs = null;
        this.listAccount = null;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * from Account ";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while(rs.next()){
                    String username = rs.getString("Username");
                    String password = rs.getString("Password");
                    String name = rs.getString("Name");
                    String role = rs.getString("Role");
                    boolean status = rs.getBoolean("Status");
                    AccountDTO dto = new AccountDTO(username, password, name, role, status);
                    if (listAccount == null) {
                        listAccount = new ArrayList<>();
                    }
                    listAccount.add(dto);
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
    
    public boolean checkExistAccount(AccountDTO account) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            con = DBUtil.makeConnection();
            if (con != null) {
                String sql = "Select * from account "
                        + "Where username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, account.getUsername());               
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
