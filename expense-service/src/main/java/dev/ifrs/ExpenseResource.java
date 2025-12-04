package dev.ifrs;

import java.util.List;

import dev.ifrs.model.Expense;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/expenses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExpenseResource {

    // CREATE
    @POST
    @WithTransaction
    public Uni<Expense> create(Expense expense) {
        return expense.persistAndFlush();
    }

    // LIST ALL
    @GET
    public Uni<List<Expense>> list() {
        return Expense.findAll().list();
    }

    // FIND BY ID
    @GET
    @Path("/{id}")
    public Uni<Expense> findById(@PathParam("id") Long id) {
        return Expense.findById(id);
    }

    // UPDATE
    @PUT
    @Path("/{id}")
    @WithTransaction
    public Uni<Expense> update(@PathParam("id") Long id, Expense updated) {
        return Expense.<Expense>findById(id)
            .onItem().ifNotNull()
            .call(item -> {
                item.setDescription(updated.getDescription());
                item.setAmount(updated.getAmount());
                item.setCategory(updated.getCategory());
                return item.persistAndFlush();
            });
    }

    // DELETE
    @DELETE
    @Path("/{id}")
    @WithTransaction
    public Uni<Boolean> delete(@PathParam("id") Long id) {
        return Expense.<Expense>findById(id)
            .onItem().ifNotNull()
            .transformToUni(item -> item.delete())
            .replaceWith(true);
    }

    // TOTAL AMOUNT
    @GET
    @Path("/total")
    public Uni<Double> total() {
        return Expense.<Expense>findAll().list()
            .map(list -> list.stream()
                .mapToDouble(Expense::getAmount)
                .sum());
    }
}
