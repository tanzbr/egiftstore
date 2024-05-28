package me.caua.egiftstore.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.caua.egiftstore.dto.in.AuthUserDTO;
import me.caua.egiftstore.dto.out.UserResponseDTO;
import me.caua.egiftstore.service.EmployeeService;
import me.caua.egiftstore.service.HashService;
import me.caua.egiftstore.service.JwtService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthResource {

    @Inject
    public HashService hashService;
    @Inject
    public EmployeeService employeeService;
    @Inject
    public JwtService jwtService;

    @POST
    public Response create(AuthUserDTO authUserDTO) {

        String hash = hashService.getHashSenha(authUserDTO.senha());
        UserResponseDTO user = null;

        // 1 = employee
        if (authUserDTO.perfil() == 0) {
            user = employeeService.login(authUserDTO.email(), hash);
            // 2 = customer
        } else if (authUserDTO.perfil() == 1) {
            // to-do
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response
                .ok(user)
                .header("Authorization", jwtService.generateJwt(user))
                .build();
    }

}
