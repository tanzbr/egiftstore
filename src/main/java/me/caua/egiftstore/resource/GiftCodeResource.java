package me.caua.egiftstore.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.caua.egiftstore.dto.in.GiftCodeDTO;
import me.caua.egiftstore.service.GiftCodeService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/giftcode")
public class GiftCodeResource {

    @Inject
    public GiftCodeService giftCodeService;

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(giftCodeService.findById(id))
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @GET
    @Path("/search/giftcard/{id}")
    public Response findByGiftCard(@PathParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(giftCodeService.findByGiftCard(id))
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @GET
    public Response findAll() {
        return Response
                .status(Response.Status.OK)
                .entity(giftCodeService.findAll())
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @POST
    public Response create(@Valid GiftCodeDTO giftCodeDTO) {
        return Response
                .status(Response.Status.CREATED)
                .entity(giftCodeService.create(giftCodeDTO))
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid GiftCodeDTO giftCodeDTO) {
        giftCodeService.update(id, giftCodeDTO);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        giftCodeService.delete(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

}
