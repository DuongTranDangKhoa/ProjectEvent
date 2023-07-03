/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.sale;

import com.swp391.demo.dao.CardDAO;
import com.swp391.demo.dao.ComboDAO;
import com.swp391.demo.dao.OrderDAO;
import com.swp391.demo.dao.OrderDetailDAO;
import com.swp391.demo.dao.ProductDAO;
import com.swp391.demo.dao.TransactionDAO;
import com.swp391.demo.dto.ComboDTO;
import com.swp391.demo.dto.OrderCheckDTO;
import com.swp391.demo.dto.OrderDetailCheckDTO;
import com.swp391.demo.dto.OrderDetailDTO;
import com.swp391.demo.dto.ProductDTO;
import com.swp391.demo.dto.TransactionDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
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
@Path("v1/sale")
public class OrderResource {

    private OrderDAO dao = OrderDAO.getInstance();
    private OrderDetailDAO dao1 = OrderDetailDAO.getInstance();
    private ProductDAO dao2 = ProductDAO.getInstance();
    private ComboDAO dao3 = ComboDAO.getInstance();
    private CardDAO dao4 = CardDAO.getInstance();
    private TransactionDAO dao5 = TransactionDAO.getInstance();

    @Path("order")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response orderListProduct(List<OrderCheckDTO> list) throws SQLException {
//        dao2.getProductShop(list.get(0).getShopId());
//        List<ProductDTO> pl = dao2.getProductList();
        int id = dao.checkOrderId();
        System.out.println(id);
        boolean result = dao.createOrder(list.get(0), id);
        if (!result) {
            return Response.status(406, "Can not Create Order").build();
        }
        TransactionDTO t = new TransactionDTO(list.get(0).getCardId(), id);
        boolean result1 = dao5.createTransaction(t);
        if (!result1) {
            return Response.status(406, "Can not Create Transaction").build();
        }
        boolean result2 = dao4.withdraw(list.get(0).getCardId(),
                dao4.getbalance(list.get(0).getCardId()) - list.get(0).getTotal());
        if (!result2) {
            return Response.status(406, "Can not withdraw money").build();
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).getCategory().equals("Combo")) {
                dao3.getInfoCombo(list.get(i).getProductId());
                List<ComboDTO> cb = dao3.getListCombo();
                for (int j = 0; j < cb.size(); j++) {
                    OrderDetailDTO dto = new OrderDetailDTO(id,
                            cb.get(j).getIdMake(),
                            list.get(i).getQuantity() * cb.get(j).getQuantity(),
                            dao2.getPrice(cb.get(j).getIdMake()),
                            0);
                    boolean x = dao1.createDetail(dto);
                    if (x == false) {
                        return Response.status(406, "Fail when insetrt orderdetail Combo").build();
                    }
                }
            } else {
                OrderDetailDTO dto = new OrderDetailDTO(id,
                        list.get(i).getProductId(),
                        list.get(i).getQuantity(),
                        dao2.getPrice(list.get(i).getProductId()),
                        0);
                boolean x = dao1.createDetail(dto);
                if (x == false) {
                    return Response.status(406, "Fail when insetrt orderdetail Product").build();
                }
            }
        }
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @Path("checkProductSold/{shopId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkProductSold(@PathParam("shopId") String shopId) throws SQLException {
        dao2.getAllProductShop(shopId);
        List<ProductDTO> productList = dao2.getAllProductList();
        dao1.viewQuatityProduct(shopId);
        List<OrderDetailDTO> productSoldList = dao1.getListProductSold();
        List<OrderDetailCheckDTO> list = new ArrayList<>();
        for (OrderDetailDTO x : productSoldList) {
            for (ProductDTO i : productList) {
                if (x.getProductId() == i.getId()) {
                    OrderDetailCheckDTO dto = new OrderDetailCheckDTO(x.getProductId(), i.getShopId(), i.getName(), i.getImg(), i.getDescription(), i.getCategory(), x.getQuantity());
                    list.add(dto);
                    break;
                }
            }
        }
        return Response.ok(list).build();
    }
}
