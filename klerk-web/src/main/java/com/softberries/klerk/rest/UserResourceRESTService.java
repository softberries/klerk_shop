package com.softberries.klerk.rest;

import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.softberries.klerk.domain.StoreUser;
import com.softberries.klerk.repository.UserRepository;

/**
 * JAX-RS User Management
 * 
 * This class produces a RESTful service to read data related to store registered users
 */
@Path("/users")
@RequestScoped
public class UserResourceRESTService {
    @Inject
    private UserRepository userRepository;

    @GET
    @Produces( { MediaType.APPLICATION_XML })
    public Set<StoreUser> listAllUsers() {
        final Set<StoreUser> results = userRepository.fetchAll();
        return results;
    }

    @GET
    @Path("/{id:[1-9][0-9]*}")
    public StoreUser lookupUserById(@PathParam("id") long id) {
        return userRepository.getById(id);
    }
}
