/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.admin;

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
@Path("v1/admin/card")
public class CardResource {

    private CardDAO dao = CardDAO.getInstance();

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCard(CardDTO dto) throws SQLException {
        boolean result = dao.CreateCard(dto);
        if (result == true) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("create50")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create50Card(CardDTO dto) throws SQLException {
        for (int i = 0; i < 50; i++) {
            boolean result = dao.CreateCard(dto);
            if (result == false) {
                return Response.status(406,"Fail to Create Create Card").build();
            }
        }
        return Response.status(Response.Status.CREATED).build();
    }
}
