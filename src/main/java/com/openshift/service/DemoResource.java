package com.openshift.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import com.openshift.helpers.Load;
/**
 * A JAX-RS resource for exposing REST endpoints for Task manipulation
 */
@Path("/demo/")
public class DemoResource {

    @GET
    @Path("load/{seconds}")
    @Produces({ "application/json" })
    public String generateLoad(@Context SecurityContext context, @PathParam("seconds") int seconds) {
    	Load cpuLoad = new Load();
    	cpuLoad.generateLoad(seconds*1000);
        return new String("Load generated for " + seconds + " seconds.");
    }
    

}
