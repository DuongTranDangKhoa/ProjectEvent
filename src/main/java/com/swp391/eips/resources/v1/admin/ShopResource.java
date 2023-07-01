/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.admin;

import com.swp391.eips.dao.ShopDAO;
import com.swp391.eips.dto.ShopDTO;
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
