/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.admin;

import com.swp391.eips.dao.AccountShopDAO;
import com.swp391.eips.dto.AccountShopDTO;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
