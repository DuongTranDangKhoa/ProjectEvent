/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v2.sale;

import com.swp391.demo.dao.ComboDAO;
import com.swp391.demo.dao.ProductDAO;
import com.swp391.demo.dto.AccountShopDTO;
import com.swp391.demo.dto.ComboDTO;
import com.swp391.demo.dto.OrderCheckDTO;
import com.swp391.demo.dto.ProductComboDTO;
import com.swp391.demo.dto.ProductDTO;
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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lnhtr
 */
@Path("v2/sale/product")
public class ProductResource {

    private List<ProductDTO> list = new ArrayList<>();

    private ProductDAO dao = ProductDAO.getInstance();
    private ComboDAO dao1 = ComboDAO.getInstance();

    @Context
    private UriInfo ui;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{shopId}")
    public Response showAllProduct(@PathParam("shopId") String id) throws SQLException {
        dao.getAllProductShop(id);
        List<ProductDTO> list = dao.getAllProductList();
        return Response.ok(list).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sell/{shopId}")
    public Response showSaleProduct(@PathParam("shopId") String id) throws SQLException {
        dao.getSaleProductShop(id);
        List<ProductDTO> list = dao.getProductSaleList();

        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("setStatus")
    public Response updateProductStatus(ProductDTO dto) throws SQLException {
        boolean result = dao.setProductStatus(dto);
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }

        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("createProduct")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductDTO dto) throws SQLException, URISyntaxException {
        boolean result = dao.creatProduct(dto);
        if (result) {
            URI uri = new URI(ui.getBaseUri() + "v2/sale/product/" + dto.getShopId());
            return Response.seeOther(uri).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Path("createCombo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCombo(List<ProductComboDTO> pc) throws SQLException, URISyntaxException {
        boolean result = dao.creatProduct(pc.get(0));
        if (result) {
            int id = dao.getIdProduct(pc.get(0));
            for (int i = 1; i < pc.size(); i++) {
                result = dao1.addComboProduct(pc.get(i), id);
            }
            if (result) {
                URI uri = new URI(ui.getBaseUri() + "v2/sale/product/" + pc.get(0).getShopId());
                return Response.seeOther(uri).build();
            }
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

}
