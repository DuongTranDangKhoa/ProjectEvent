/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.admin;

import com.swp391.demo.dao.AccountDAO;
import com.swp391.demo.dto.AccountDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
@Path("v1/admin/account")
public class AccountResource {

    private AccountDAO dao = AccountDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountDTO dto) throws SQLException {

        AccountDTO x = dao.CheckLogin(dto);

        if (x == null) {
            dao.createAccount(dto);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

}
