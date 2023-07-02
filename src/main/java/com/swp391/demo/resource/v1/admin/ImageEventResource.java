/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.admin;

import com.swp391.demo.dao.ImageEventDAO;
import com.swp391.demo.dto.ImageEventDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lnhtr
 */
@Path("v1/admin/img")
public class ImageEventResource {

    private ImageEventDAO dao = ImageEventDAO.getInstance();

    @Path("{eventId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewImageEvent(@PathParam("eventId") int eventId) throws SQLException {
        dao.listImage(eventId);
        List<ImageEventDTO> list = dao.getImageList();
        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addImageEvent(ImageEventDTO dto) throws SQLException {
        boolean result = dao.addImage(dto);
        if (result) {
            return Response.status(Response.Status.CREATED).build();

        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
