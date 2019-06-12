package fr.coding.bankaccount.controller;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.services.AccountService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("accounts")
public class AccountController {
    @POST
    @Path("/{accountID}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(@PathParam("accountID") Long accountID, final Parameter param) {

        AccountService accountService = new AccountService(null, null);

        try {
            accountService.deposit(accountID, param.getValue());
        } catch (AmountNegativeException | AccountNotFoundException e) {

        }

        return Response.ok().build();
    }
}
