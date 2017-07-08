package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.FoobarSerice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Yisa on 2017/7/8.
 */

@Component
public class FooBar {

    @Autowired
    FoobarSerice foobarSerice;

    @GET
    @Path("/{param}")
    public Response getMessage(@PathParam("param") String msg){
        return Response.status(200).entity(foobarSerice.getMessage(msg)).build();
    }

    public FoobarSerice getFoobarSerice() {
        return foobarSerice;
    }

    public void setFoobarSerice(FoobarSerice foobarSerice) {
        this.foobarSerice = foobarSerice;
    }
}
