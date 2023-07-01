/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.sale;

import com.swp391.eips.dao.ComboDAO;
import com.swp391.eips.dao.ProductDAO;
import com.swp391.eips.dto.AccountShopDTO;
import com.swp391.eips.dto.ComboDTO;
import com.swp391.eips.dto.OrderCheckDTO;
import com.swp391.eips.dto.ProductComboDTO;
import com.swp391.eips.dto.ProductDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        dao.getAllProductShop(dto.getShopId());
        List<ProductDTO> list = dao.getAllProductList();

        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("sale")
    public Response showSaleProduct(AccountShopDTO dto) throws SQLException {
        dao.getSaleProductShop(dto.getShopId());
        List<ProductDTO> list = dao.getProductSaleList();

        return Response.ok(list).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update")
    public Response updateProductStatus(ProductDTO dto) throws SQLException {
        boolean result = dao.updateProduct(dto);
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

    @Path("createCombo")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCombo(List<ProductComboDTO> pc) throws SQLException {
        boolean result = dao.creatProduct(pc.get(0));
        if (result) {
            int id = dao.getIdProduct(pc.get(0));
            System.out.println(id);
            for (int i = 1; i < pc.size(); i++) {
                System.out.println(pc.get(i).toString());
                result = dao1.addComboProduct(pc.get(i), id);
                System.out.println(result);
            }
            if (result) {
                return Response.status(Response.Status.CREATED).build();
            }
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
    
    

}
