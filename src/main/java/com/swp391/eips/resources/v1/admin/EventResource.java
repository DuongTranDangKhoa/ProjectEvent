/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.admin;

import com.swp391.eips.dao.EventDAO;
import com.swp391.eips.dto.EventDTO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lnhtr
 */
@Path("v1/admin/event")
public class EventResource {

    private EventDAO dao = EventDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvent(EventDTO dto) throws SQLException {
        boolean result = dao.createEvent(dto);
        if (result) {
            return Response.status(Response.Status.CREATED).build();

        }
        return Response.status(406, "Event can not create").build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEvent() throws SQLException {

        dao.getAllEvent();
        List<EventDTO> list = dao.getListEvent();
        if (list.isEmpty()) {
            return Response.status(406, "No Event in Project").build();

        }
        return Response.ok(list).build();

    }

    @Path("update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEvent(EventDTO dto) throws SQLException {
        boolean result = dao.updateEvent(dto);
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(406, "Fail to update Event").build();
    }

}
