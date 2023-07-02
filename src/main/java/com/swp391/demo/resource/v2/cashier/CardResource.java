/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v2.cashier;

import com.swp391.demo.dao.CardDAO;
import com.swp391.demo.dto.AccountDTO;
import com.swp391.demo.dto.CardDTO;
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
@Path("v2/cashier/card")
public class CardResource {

    private CardDAO dao = CardDAO.getInstance();

    @Context
    private UriInfo ui;



    @Path("update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCard(CardDTO dto) throws SQLException, URISyntaxException {
        boolean result = dao.UpdateCard(dto);
        if (result) {
            URI uri = new URI(ui.getBaseUri() + "v2/general/" + dto.getId());
            return Response.seeOther(uri).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("deposite")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response depositeCard(CardDTO dto) throws SQLException, URISyntaxException {
        double balance = dao.getbalance(dto.getId()) + dto.getBalance();
        boolean result = dao.deposite(dto.getId(), balance);
        if (result) {
            URI uri = new URI(ui.getBaseUri() + "v2/general/" + dto.getId());
            return Response.seeOther(uri).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }

    @Path("withdraw")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response withdrawCard(CardDTO dto) throws SQLException, URISyntaxException {
        double balance = dao.getbalance(dto.getId()) - dto.getBalance();
        boolean result = dao.withdraw(dto.getId(), balance);
        if (result) {
            URI uri = new URI(ui.getBaseUri() + "v2/general/" + dto.getId());
            return Response.seeOther(uri).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }

}
