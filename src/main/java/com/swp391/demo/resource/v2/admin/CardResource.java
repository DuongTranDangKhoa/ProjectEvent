/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v2.admin;

import com.swp391.demo.dao.CardDAO;
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
import java.util.List;

/**
 *
 * @author lnhtr
 */
@Path("v2/admin/card")
public class CardResource {

    private CardDAO dao = CardDAO.getInstance();
    
    @Context
    private UriInfo ui;

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCard(CardDTO dto) throws SQLException, URISyntaxException {
        boolean result = dao.CreateCard(dto);
        if (result == true) {
            URI uri = new URI(ui.getBaseUri() + "v2/admin/card");
            return Response.seeOther(uri).build();
            
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListCard() throws SQLException{
        dao.getAllCard();
        List<CardDTO> list = dao.getListCard();
        if (!list.isEmpty()) {
            return Response.ok(list).build();
        }
        return Response.status(200, "Not exist Account").build();
    }
}
