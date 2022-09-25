package com.github.sanchezih.jersey.service;

import com.github.sanchezih.jersey.model.UserEntity;
import com.github.sanchezih.jersey.util.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserService {

    // Vi este sistema de maps en el trabajo de un compañero y me encantó ¡Super práctico! <3
    private static final Map<String, UserEntity> USERS = new HashMap<>();

    public UserService() {
        USERS.put("mperez", new UserEntity("mperez", "Mariela"));
        USERS.put("jlopez", new UserEntity("jlopez", "Juan"));
        USERS.put("savila", new UserEntity("savila", "Sol"));
    }

    // Reordené los metodos por puro capricho. CRUD -> Create, Read, Update, Delete. 
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(UserEntity userRequest) {
        if (USERS.get(userRequest.getUsername()) != null) {
            return Response.status(Status.BAD_REQUEST).entity(Constants.USER_ALREADY_EXISTS).build();
        }
        USERS.put(userRequest.getUsername(), userRequest);
        return Response.ok(new ArrayList<>(USERS.values())).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.ok(new ArrayList<>(USERS.values())).build();
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByUsername(@PathParam("username") String username) {
        if (USERS.get(username) == null) {
            return Response.status(Status.BAD_REQUEST).entity(Constants.USER_NOT_FOUND).build();
        }
        return Response.ok(USERS.get(username)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UserEntity userEntity) {
        UserEntity found = USERS.get(userEntity.getUsername());
        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity(Constants.USER_NOT_FOUND).build();
        }
        found.setName(userEntity.getName());
        return Response.ok(found).build();
    }

    @DELETE
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("username") String username) {
        UserEntity found = USERS.get(username);
        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity(Constants.USER_NOT_FOUND).build();
        }
        USERS.remove(username);
        return Response.ok(new ArrayList<>(USERS.values())).build();
    }

}