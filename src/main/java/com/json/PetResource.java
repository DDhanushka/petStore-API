package com.json;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

    private Set<Pet> pets = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));


    public PetResource() {
        pets.add(new Pet(1, "Tyga", 3, "cat"));
        pets.add(new Pet(2, "Bula", 1, "dog"));
        pets.add(new Pet(3, "Tommy", 2, "dog"));
        pets.add(new Pet(4, "Suddi", 2, "cat"));
    }

    @GET
    public Response list() {

        if (pets.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(pets).build();
    }

    @GET
    @Path("/{name}")
    public Response getOne(@PathParam String name) {

        Set<Pet> responseSet = pets.stream().filter(pet -> pet.getName().equals(name)).collect(Collectors.toSet());
        if (responseSet.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(responseSet).build();
    }

    @GET
    @Path("/findByType/{petType}")
    public Response getByType(@PathParam String petType) {

        Set<Pet> responseSet = pets.stream().filter(pet -> pet.getPetType().equals(petType.toLowerCase())).collect(Collectors.toSet());
        if (responseSet.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(responseSet).build();
    }

    @POST
    public Response add(Pet pet) {

        pets.add(pet);
        return Response.ok(pets).build();
    }

    @DELETE
    @Path("/delete/{petID}")
    public Response delete(@PathParam("petID") Integer petID) {

        if (pets.isEmpty() || petID < 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        pets.removeIf(petItem -> petItem.getPetID().equals(petID));
        return Response.ok(pets).build();
    }

    @PUT
    @Path("/update/{petID}/{petName}")
    public Response updatePetName(@PathParam("petID") Integer petID, @PathParam("petName") String petName) {
        if (petID < 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        pets.forEach(pet -> {
            if (pet.getPetID() == petID) {
                pet.setName(petName);
            }
        });
        return Response.ok(pets).build();
    }

}
