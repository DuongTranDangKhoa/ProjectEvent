/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.cashier;

import com.swp391.demo.dao.CardDAO;
import com.swp391.demo.dto.CardDTO;
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
@Path("v1/cashier/card")
public class CardResource {

    private CardDAO dao = CardDAO.getInstance();

    @Path("update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCard(CardDTO dto) throws SQLException {
        boolean result = dao.UpdateCard(dto);
        if (result) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("deposite")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response depositeCard(CardDTO dto) throws SQLException {
        boolean result = dao.deposite(dto);
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }

    @Path("withdraw")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdrawCard(CardDTO dto) throws SQLException {
        boolean result = dao.withdraw(dto);
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }
}
