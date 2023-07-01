/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.swp391.eips.resources.v1.admin;

import com.swp391.eips.dao.AccountDAO;
import com.swp391.eips.dao.AccountSetDAO;
import com.swp391.eips.dto.AccountDTO;
import com.swp391.eips.dto.AccountSetDTO;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author lnhtr
 */
@Path("v1/admin/account")
public class AccountResource {

    private AccountDAO dao = AccountDAO.getInstance();
    private AccountSetDAO dao1 = AccountSetDAO.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountDTO dto) throws SQLException {

        boolean result = dao.checkExistAccount(dto);

        if (!result) {
            dao.createAccount(dto);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(406, "Account has existed").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListAccount() throws SQLException {
        dao.getAllListAccount();
        List<AccountDTO> list = dao.getListAccount();
        return Response.ok(list).build();
    }

    @Path("set")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAccountSet(AccountSetDTO dto) throws SQLException {

        boolean result = dao1.checkExistAccount(dto);

        if (!result) {
            dao1.createAccount(dto);
            dao1.setAcccountShop(dto);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(406, "Account has existed").build();
    }

    @Path("update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccount(AccountDTO dto) throws SQLException {

        boolean result = dao.updateAccount(dto);

        if (result) {
            
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(406, "Fail to update Account").build();
    }
}
