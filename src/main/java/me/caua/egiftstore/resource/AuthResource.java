package me.caua.egiftstore.resource;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.caua.egiftstore.dto.AuthUsuarioDTO;
import me.caua.egiftstore.dto.EmployeeDTO;
import me.caua.egiftstore.dto.UserResponseDTO;
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
    public Response create(AuthUsuarioDTO authUsuarioDTO) {

        String hash = hashService.getHashSenha(authUsuarioDTO.senha());
        UserResponseDTO user = null;

        // 1 = employee
        if (authUsuarioDTO.perfil() == 1) {
            user = employeeService.login(authUsuarioDTO.email(), authUsuarioDTO.senha());
            // 2 = customer
        } else if (authUsuarioDTO.perfil() == 2) {

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response
                .ok(user)
                .header("Authorization", jwtService.generateJwt(user))
                .build();
    }

}
