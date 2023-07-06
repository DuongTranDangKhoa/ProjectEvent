/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.sale;

import com.swp391.demo.dao.EventDAO;
import com.swp391.demo.dao.ShopDAO;
import com.swp391.demo.dto.EventDTO;
import com.swp391.demo.dto.ShopDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
@Path("v1/sale/info")
public class InfoResource {

    private ShopDAO dao = ShopDAO.getInstance();
    private EventDAO dao1 = EventDAO.getInstance();

    @Path("shop/{username}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShop(@PathParam("username") String username) throws SQLException {
        ShopDTO dto = dao.getShopByUsername(username);
        if (dto != null) {
            return Response.ok(dto).build();
        }
        return Response.status(406, "Account didnt have relationship with any Shop or Account has not exist").build();

    }
    
    @Path("event/{shopId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEvent(@PathParam("shopId") String shopId) throws SQLException {
        EventDTO dto = dao1.getEventByShopId(shopId);
        if (dto != null) {
            return Response.ok(dto).build();
        }
        return Response.status(406, "Not found").build();

    }
}
