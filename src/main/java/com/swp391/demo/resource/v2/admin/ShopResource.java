/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v2.admin;

import com.swp391.demo.dao.ShopDAO;
import com.swp391.demo.dto.ShopDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
@Path("v2/admin/shop")
public class ShopResource {

    private ShopDAO dao = ShopDAO.getInstance();
    
    @Context
    private UriInfo ui;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShop(ShopDTO dto) throws SQLException, URISyntaxException {
        String result = dao.checkShopExist(dto);
        if (result == null) {
            dao.createShop(dto);
            URI uri = new URI(ui.getBaseUri() + "v2/admin/shop/" + dto.getEventId());
            return Response.seeOther(uri).build();
        }

        return Response.status(406, "Shop name has exist: " + result).build();
//        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("{eventId}")
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShopDTO> getListShop(@PathParam("eventId") int id) throws SQLException {
        dao.getShopInEvent(id);

        return dao.getShopList();
    }
    
    
}
