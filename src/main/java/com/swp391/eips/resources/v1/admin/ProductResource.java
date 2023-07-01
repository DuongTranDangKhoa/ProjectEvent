/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.admin;

import com.swp391.eips.dao.ProductDAO;
import com.swp391.eips.dto.ProductDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lnhtr
 */
@Path("v1/admin/productInShop")
public class ProductResource {

    private ProductDAO dao = ProductDAO.getInstance();
    private List<ProductDTO> list = new ArrayList<>();

    @Path("{shopId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("shopId") String shopId) throws SQLException {
        dao.getSaleProductShop(shopId);
        List<ProductDTO> list = dao.getProductSaleList();

        return Response.ok(list).build();
    }

}
