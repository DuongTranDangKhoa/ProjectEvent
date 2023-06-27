/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.admin;

import com.swp391.demo.dao.EventDAO;
import com.swp391.demo.dto.EventDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

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

}
