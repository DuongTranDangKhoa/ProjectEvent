/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.demo.resource.v1.admin;

import com.swp391.demo.dao.AccountShopDAO;
import com.swp391.demo.dto.AccountShopDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

/**
 *
 * @author lnhtr
 */
@Path("v1/admin/setStatus")
public class AccountShopResource {

    private AccountShopDAO dao = AccountShopDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setStatus(AccountShopDTO dto) throws SQLException {
        if (dto.isStatus()) {
            boolean i = dao.checkStatusExist(dto.getUsername());
            if (i) {
                return Response.status(406, "Account has having relationship with other shop").build();
            }
        }
        boolean result = dao.setStatus(dto);
        if (result) {
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }
}
