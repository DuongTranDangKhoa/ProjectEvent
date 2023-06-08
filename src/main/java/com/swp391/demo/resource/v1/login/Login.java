/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.login;

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
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */

@Path("v1/login")
public class Login {

    private AccountDAO dao = AccountDAO.getInstance();

    @Context
    private UriInfo ui;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDTO getBook() {
        return new AccountDTO("123", "123", "toi hom qua", "sales", true);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response checkLogin(AccountDTO dto) throws SQLException, URISyntaxException {
        AccountDTO x = dao.CheckLogin(dto);
        if (x == null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.accepted(x).build();
    }
}
