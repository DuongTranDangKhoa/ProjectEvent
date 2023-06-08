/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.sale;

import com.swp391.demo.dao.ComboDAO;
import com.swp391.demo.dao.ProductDAO;
import com.swp391.demo.dto.AccountShopDTO;
import com.swp391.demo.dto.ComboDTO;
import com.swp391.demo.dto.ProductDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lnhtr
 */
@Path("v1/sale/product")
public class ProductResource {

    private List<ProductDTO> list = new ArrayList<>();

    private ProductDAO dao = ProductDAO.getInstance();
    private ComboDAO dao1 = ComboDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response showAllProduct(AccountShopDTO dto) throws SQLException {
        dao.getAllProductShop(dto);
        List<ProductDTO> list = dao.getAllProductList();

        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sale")
    public Response showSaleProduct(AccountShopDTO dto) throws SQLException {
        dao.getSaleProductShop(dto);
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
    public Response createProduct(ProductDTO dto) throws SQLException {
        boolean result = dao.creatProduct(dto);
        if (result) {
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

//    @Path("createCombo")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response createCombo(ProductDTO pd, List<ComboDTO> dc) throws SQLException {
//        boolean result = dao.creatProduct(pd);
//        System.out.println(result);
//        if (result) {
//            int id = dao.getIdProduct(pd);
//            System.out.println(id);
//            result = dao1.addComboProduct(dc, id);
//            if (result) {
//                return Response.status(Response.Status.CREATED).build();
//            }
//        }
//        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
//
//    }
}
