package me.caua.egiftstore.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.caua.egiftstore.dto.in.GiftCompanyDTO;
import me.caua.egiftstore.form.ImageForm;
import me.caua.egiftstore.model.GiftCompany;
import me.caua.egiftstore.service.GiftCardFileServiceImpl;
import me.caua.egiftstore.service.GiftCompanyFileServiceImpl;
import me.caua.egiftstore.service.GiftCompanyService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/giftcompany")
public class GiftCompanyResource {

    @Inject
    public GiftCompanyService giftCompanyService;
    @Inject
    public GiftCompanyFileServiceImpl fileService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(giftCompanyService.findById(id))
                .build();
    }

    @GET
    @Path("/search/name/{name}")
    public Response findByName(@PathParam("name") String name) {
        return Response
                .status(Response.Status.OK)
                .entity(giftCompanyService.findByName(name))
                .build();
    }

    @GET
    public Response findAll() {
        return Response
                .status(Response.Status.OK)
                .entity(giftCompanyService.findAll())
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @POST
    public Response create(@Valid GiftCompanyDTO giftCompanyDTO) {
        return Response
                .status(Response.Status.CREATED)
                .entity(giftCompanyService.create(giftCompanyDTO))
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid GiftCompanyDTO giftCompanyDTO) {
        giftCompanyService.update(id, giftCompanyDTO);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        giftCompanyService.delete(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @RolesAllowed({"SALES", "MANAGER", "CEO"})
    @PATCH
    @Path("/{id}/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response saveImage(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        fileService.save(id, form.getImageName(), form.getImage());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{imageName}")
    public Response download(@PathParam("imageName") String imageName) {
        Response.ResponseBuilder responseBuilder = Response.ok(fileService.download(imageName));
        responseBuilder.header("Content-Disposition", "attachment;filename="+imageName);
        return responseBuilder.build();
    }

}
