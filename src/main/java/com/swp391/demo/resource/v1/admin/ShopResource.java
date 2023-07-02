/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.admin;

import com.swp391.demo.dao.ShopDAO;
import com.swp391.demo.dto.ShopDTO;
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
@Path("v1/admin/shop")
public class ShopResource {

    private ShopDAO dao = ShopDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShop(ShopDTO dto) throws SQLException {
        String result = dao.checkShopExist(dto);
        if (result == null) {
            dao.createShop(dto);
            return Response.status(Response.Status.CREATED).build();
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
