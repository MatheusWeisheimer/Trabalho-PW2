package dev.ifrs;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import dev.ifrs.client.ExpenseClient;
import dev.ifrs.dto.ExpenseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/reports")
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {

    @RestClient
    ExpenseClient expenseClient;

    // Get all expenses via Expense Service
    @GET
    @Path("/all")
    public List<ExpenseDTO> all() {
        return expenseClient.listAll();
    }

    // Get total of all expenses
    @GET
    @Path("/total")
    public Double total() {
        return expenseClient.listAll()
            .stream()
            .mapToDouble(e -> e.amount)
            .sum();
    }

    // Total by category
    @GET
    @Path("/by-category")
    public Map<String, Double> totalByCategory() {
        return expenseClient.listAll()
            .stream()
            .collect(Collectors.groupingBy(
                e -> e.category,
                Collectors.summingDouble(e -> e.amount)
            ));
    }
}
