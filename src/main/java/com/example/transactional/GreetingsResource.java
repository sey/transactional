package com.example.transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Consumes({MediaType.APPLICATION_JSON_VALUE})
@Produces({MediaType.APPLICATION_JSON_VALUE})
@Path("/")
@Component
public class GreetingsResource {

    private GreetingsService greetingsService;

    @Autowired
    public GreetingsResource(GreetingsService greetingsService) {
        this.greetingsService = greetingsService;
    }

    @POST
    @Path("/greetings")
    public Greeting create(Greeting greeting) {
        return greetingsService.create(greeting);
    }

    @GET
    @Path("/greetings")
    public List<Greeting> getAll() {
        return greetingsService.getAll();
    }
}
