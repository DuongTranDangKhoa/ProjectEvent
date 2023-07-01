/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.sale;

import com.swp391.eips.dao.CardDAO;
import com.swp391.eips.dao.ComboDAO;
import com.swp391.eips.dao.OrderDAO;
import com.swp391.eips.dao.OrderDetailDAO;
import com.swp391.eips.dao.ProductDAO;
import com.swp391.eips.dao.TransactionDAO;
import com.swp391.eips.dto.ComboDTO;
import com.swp391.eips.dto.OrderCheckDTO;
import com.swp391.eips.dto.OrderDetailDTO;
import com.swp391.eips.dto.ProductDTO;
import com.swp391.eips.dto.TransactionDTO;
import java.sql.SQLException;
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
}
