/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.admin;

import com.swp391.eips.dao.ImageEventDAO;
import com.swp391.eips.dto.ImageEventDTO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
