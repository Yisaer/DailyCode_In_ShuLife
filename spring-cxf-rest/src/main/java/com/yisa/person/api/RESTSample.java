package com.yisa.person.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Yisa on 2017/7/9.
 */

@Path("/sample")
public interface RESTSample {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String doGet();

}
