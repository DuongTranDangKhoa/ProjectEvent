/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.cashier;

import com.swp391.eips.dao.CardDAO;
import com.swp391.eips.dto.CardDTO;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        boolean pc = dao.checkCardUpdate(dto.getId());
        if (pc == true) {
            return Response.status(406, "Card updated before").build();
        }
        boolean result = dao.UpdateCard(dto);
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(406, "Fail to update card").build();
    }

    @Path("deposite")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response depositeCard(CardDTO dto) throws SQLException {
        double balance = dao.getbalance(dto.getId());
        boolean result = dao.deposite(dto.getId(), balance + dto.getBalance());
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }

    @Path("withdraw")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdrawCard(CardDTO dto) throws SQLException {
        double balance = dao.getbalance(dto.getId());
        boolean result = dao.withdraw(dto.getId(), balance - dto.getBalance());
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }
}
