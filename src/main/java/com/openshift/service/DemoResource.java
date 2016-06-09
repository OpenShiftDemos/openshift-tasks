package com.openshift.service;

import com.openshift.helpers.Load;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A JAX-RS resource for exposing REST endpoints for Task manipulation
 */
@Path("/demo/")
public class DemoResource {

    @GET
    @Path("load/{seconds}")
    @Produces({"application/json"})
    public String generateLoad(@Context SecurityContext context, @PathParam("seconds") int seconds) {
        Load cpuLoad = new Load();
        cpuLoad.generateLoad(seconds * 1000);
        return new String("Load generated for " + seconds + " seconds.");
    }

    @GET
    @Path("log/info/{number}")
    @Produces({"application/json"})
    public String logInfo(@Context SecurityContext context, @PathParam("number") int number) {
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.INFO, "OpenShift: A message for INFO purposes");
        return new String("Added a log statement of type INFO");
    }

    @GET
    @Path("log/error/{number}")
    @Produces({"application/json"})
    public String logSevere(@Context SecurityContext context, @PathParam("number") int number) {
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.SEVERE, "OpenShift: This is an error message");
        return new String("Added a log statement of type SEVERE");
    }

    @GET
    @Path("log/warning/{number}")
    @Produces({"application/json"})
    public String logWarning(@Context SecurityContext context, @PathParam("number") int number) {
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.WARNING, "OpenShift: This is a warning message");
        return new String("Added a log statement of type WARNING");
    }

    @GET
    @Path("killswitch/")
    @Produces({"application/json"})
    public String killSwitch(@Context SecurityContext context, @PathParam("number") int number) {
        System.exit(1);
        return new String("Added a log statement of type WARNING");
    }


}
