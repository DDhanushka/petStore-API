package com.json;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.annotations.Param;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {
    private Set<Fruit> fruits = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public FruitResource() {
        fruits.add(new Fruit("Apple", "Windter fruite"));
        fruits.add(new Fruit("Pineapple", "Tropical fruite"));
    }

//    @GET
//    public Set<Fruit> list() {
//        return fruits;
//    }

    @GET
    public Response list() {
        if (fruits.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(fruits).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/find/{name}")
    public Response getOne(@PathParam String name) {

        Set<Fruit> responseSet = fruits.stream().filter(item -> item.name.toLowerCase().equals(name.toLowerCase())).collect(Collectors.toSet());
        if (responseSet.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(responseSet).build();
    }

    @POST
    public Set<Fruit> add(Fruit fruit) {
        fruits.add(fruit);
        return fruits;
    }

    @DELETE
    public Set<Fruit> delete(Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(fruit.name));
        return fruits;
    }

    @PUT
    public Set<Fruit> update(String xname, Fruit fruit) {
        fruits.removeIf(existingFruit -> existingFruit.name.contentEquals(xname));
        fruits.add(new Fruit(fruit.name, fruit.description));
        return fruits;

    }
}
