package dev.ifrs.client;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import dev.ifrs.dto.ExpenseDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/expenses")
@RegisterRestClient(configKey = "expense-client")
@Produces(MediaType.APPLICATION_JSON)
public interface ExpenseClient {

    @GET
    List<ExpenseDTO> listAll();
}
