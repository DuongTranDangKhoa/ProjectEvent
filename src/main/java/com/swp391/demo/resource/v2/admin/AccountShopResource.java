/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v2.admin;

import com.swp391.demo.dao.AccountShopDAO;
import com.swp391.demo.dto.AccountShopDTO;
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
@Path("v2/admin")
public class AccountShopResource {

    private AccountShopDAO dao = AccountShopDAO.getInstance();

    @Path("setRalationship")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setRalationship(AccountShopDTO dto) throws SQLException {
        boolean result = dao.setAccountShop(dto);
        if (result) {
            return Response.status(Response.Status.CREATED).build();

        }
        return Response.status(406, "Fail to set relationship").build();

    }

    @Path("setStatus")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStatus(AccountShopDTO dto) throws SQLException{
        boolean result = dao.setStatus(dto);
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(406,"Fail to set Status in ShopAccount").build();
    }
}
