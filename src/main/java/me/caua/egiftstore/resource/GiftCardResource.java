package me.caua.egiftstore.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.caua.egiftstore.dto.in.GiftCardDTO;
import me.caua.egiftstore.service.GiftCardService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/giftcard")
public class GiftCardResource {

    @Inject
    public GiftCardService giftCardService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(giftCardService.findById(id))
                .build();
    }

    @GET
    @Path("/search/name/{name}")
    public Response findByName(@PathParam("name") String name) {
        return Response
                .status(Response.Status.OK)
                .entity(giftCardService.findByName(name))
                .build();
    }

    @GET
    public Response findAll() {
        return Response
                .status(Response.Status.OK)
                .entity(giftCardService.findAll())
                .build();
    }

    @RolesAllowed("Funcionario")
    @POST
    public Response create(@Valid GiftCardDTO giftCardDTO) {
        return Response
                .status(Response.Status.CREATED)
                .entity(giftCardService.create(giftCardDTO))
                .build();
    }

    @RolesAllowed("Funcionario")
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid GiftCardDTO giftCardDTO) {
        giftCardService.update(id, giftCardDTO);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @RolesAllowed("Funcionario")
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        giftCardService.delete(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

}
