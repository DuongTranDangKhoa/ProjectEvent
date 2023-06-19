/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.admin;

import com.swp391.demo.dao.AccountDAO;
import com.swp391.demo.dto.AccountDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import java.sql.SQLException;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author lnhtr
 */
@Path("v1/admin/account")
public class AccountResource {

    private AccountDAO dao = AccountDAO.getInstance();
    
    @Context
    private UriInfo ui; 

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountDTO dto) throws SQLException, URISyntaxException {

        boolean result = dao.checkExistAccount(dto);

        if (!result) {
            dao.createAccount(dto);
            URI uri = new URI(ui.getBaseUri() + "v1/admin/account");
            return Response.seeOther(uri).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @GET 
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListAccount() throws SQLException{
        dao.getAllListAccount();
        List<AccountDTO> list = dao.getListAccount();
        return Response.ok(list).build();
    }
}
