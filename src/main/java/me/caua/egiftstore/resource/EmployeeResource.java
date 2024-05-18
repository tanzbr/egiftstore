package me.caua.egiftstore.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.caua.egiftstore.dto.in.EmployeeDTO;
import me.caua.egiftstore.service.EmployeeService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/employee")
public class EmployeeResource {

    @Inject
    public EmployeeService employeeService;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response
                .status(Response.Status.OK)
                .entity(employeeService.findById(id))
                .build();
    }

    @GET
    @Path("/search/name/{name}")
    public Response findByName(@PathParam("name") String name) {
        return Response
                .status(Response.Status.OK)
                .entity(employeeService.findByName(name))
                .build();
    }

    @GET
    public Response findAll() {
        return Response
                .status(Response.Status.OK)
                .entity(employeeService.findAll())
                .build();
    }

    @POST
    public Response create(@Valid EmployeeDTO employeeDTO) {
        return Response
                .status(Response.Status.CREATED)
                .entity(employeeService.create(employeeDTO))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid EmployeeDTO employeeDTO) {
        employeeService.update(id, employeeDTO);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        employeeService.delete(id);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

}
