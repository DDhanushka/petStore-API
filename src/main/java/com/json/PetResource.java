package com.json;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Path("/pets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PetResource {

//    private Set<PetType> petTypes = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));
    private Set<Pet> pets = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));


    public PetResource() {
//        petTypes.add(new PetType("Cat"));
//        petTypes.add(new PetType("Dog"));
        pets.add(new Pet("Tyga", 3, "cat"));
        pets.add(new Pet("Bula", 1, "dog"));
        pets.add(new Pet("Tommy", 2, "dog"));
        pets.add(new Pet("Suddi", 2, "cat"));

    }

    @GET
    public Set<Pet> list() {
        return pets;
    }

    @GET
    @Path("/{name}")
    public Set<Pet> getOne(@PathParam String name) {
        return pets.stream().filter(pet -> pet.getName().equals(name)).collect(Collectors.toSet());
    }

    @GET
    @Path("/findByType/{petType}")
    public Set<Pet> getByType(@PathParam String petType) {
        return pets.stream().filter(pet -> pet.getPetType().equals(petType.toLowerCase())).collect(Collectors.toSet());
    }

    @POST
    public Set<Pet> add(Pet pet) {
        pets.add(pet);
        return pets;
    }

    @DELETE
    public  Set<Pet> delete(Pet pet) {
        pets.removeIf(petItem -> petItem.getName().contentEquals(pet.getName()));
        return pets;
    }

}
