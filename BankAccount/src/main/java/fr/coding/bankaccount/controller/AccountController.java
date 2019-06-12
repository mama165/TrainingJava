package fr.coding.bankaccount.controller;

import fr.coding.bankaccount.exceptions.AccountNotFoundException;
import fr.coding.bankaccount.exceptions.AmountNegativeException;
import fr.coding.bankaccount.exceptions.NotEnoughMoneyOnAccountException;
import fr.coding.bankaccount.services.AccountService;
import fr.coding.bankaccount.services.DateServiceImpl;
import fr.coding.bankaccount.services.OperationRepositoryImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("accounts")
public class AccountController {
    private final AccountService accountService = new AccountService(new OperationRepositoryImpl(), new DateServiceImpl());

    @POST
    @Path("/{accountID}/deposit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deposit(@PathParam("accountID") Long accountID, final Parameter parameter) {
        String value = parameter.getValue();

        try {
            accountService.deposit(accountID, value);
        } catch (AccountNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (AmountNegativeException e) {

        }

        return Response.ok().build();
    }

    @POST
    @Path("/{accountID}/withdraw")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response withdraw(@PathParam("accountID") Long accountID, final Parameter parameter) {
        String value = parameter.getValue();

        try {
            accountService.withdraw(accountID, value);
        } catch (AccountNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (AmountNegativeException e) {

        } catch (NotEnoughMoneyOnAccountException e) {

        }

        return Response.ok().build();
    }
}
