/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.general;

import com.swp391.eips.dao.AccountDAO;
import com.swp391.eips.dao.CardDAO;
import com.swp391.eips.dao.OrderDAO;
import com.swp391.eips.dao.ProductDAO;
import com.swp391.eips.dto.AccountDTO;
import com.swp391.eips.dto.CardDTO;
import com.swp391.eips.dto.OrderDTO;
import com.swp391.eips.dto.ProductDTO;
import java.sql.SQLException;
import java.util.ArrayList;
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
@Path("v1/general")
public class GeneralResource {

    private AccountDAO dao = AccountDAO.getInstance();
    private CardDAO dao1 = CardDAO.getInstance();
    private OrderDAO dao2 = OrderDAO.getInstance();
    private ProductDAO dao3 = ProductDAO.getInstance();
    private List<ProductDTO> list = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook() {
        AccountDTO dto = new AccountDTO("123", "123", "toi hom qua", "sales", true);
        try {
            return Response.ok(dto).build();
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }
        return Response.ok(dto).build();

    }

    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(AccountDTO dto) throws SQLException{
        AccountDTO x = dao.checkLogin(dto);
        if (x == null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.accepted(x).build();
    }

    @Path("cardInfo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchCard(CardDTO dto) throws SQLException {
        CardDTO x = dao1.getInfoCard(dto.getId());

        if (x == null) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.accepted(x).build();
    }

    @Path("viewRevenue")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewRevenue(OrderDTO dto) throws SQLException {
        OrderDTO x = dao2.viewRevenue(dto);
        if (x != null) {
            return Response.accepted(x).build();

        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();

    }

    @Path("{shopId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("shopId") String shopId) throws SQLException {
        dao3.getSaleProductShop(shopId);
        List<ProductDTO> list = dao3.getProductSaleList();

        return Response.ok(list).build();
    }
}
