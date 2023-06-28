/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.general;

import com.swp391.demo.dao.AccountDAO;
import com.swp391.demo.dao.CardDAO;
import com.swp391.demo.dao.OrderDAO;
import com.swp391.demo.dao.ProductDAO;
import com.swp391.demo.dto.AccountDTO;
import com.swp391.demo.dto.CardDTO;
import com.swp391.demo.dto.OrderDTO;
import com.swp391.demo.dto.ProductDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    public AccountDTO getBook() {
        return new AccountDTO("123", "123", "toi hom qua", "sales", true);
    }

    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkLogin(AccountDTO dto) throws SQLException, URISyntaxException {
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
    public Response viewRevenue(List<OrderDTO> list) throws SQLException {
        dao2.viewRevenue(list);
        List<OrderDTO> x = dao2.getListRevenue();
        if (x != null) {
            System.out.println(x.get(0).getBeginDate());
            System.out.println(x.get(0).getEndDate());
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