package me.caua.egiftstore.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.caua.egiftstore.dto.in.GiftCodeDTO;
import me.caua.egiftstore.dto.in.OrderDTO;
import me.caua.egiftstore.service.GiftCodeService;
import me.caua.egiftstore.service.OrderService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/order")
public class OrderResource {

    @Inject
    public OrderService orderService;

    @RolesAllowed({"CUSTOMER", "SALES", "MANAGER", "CEO"})
    @POST
    public Response create(@Valid OrderDTO orderDTO) {
        return Response
                .status(Response.Status.CREATED)
                .entity(orderService.create(orderDTO))
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(orderService.findById(id))
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @GET
    public Response findAll() {
        return Response
                .status(Response.Status.OK)
                .entity(orderService.findAll())
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @GET
    public Response findByCustomer(Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(orderService.findByCustomerId(id))
                .build();
    }

}
