package com.openshift.service;

import com.openshift.helpers.Load;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A JAX-RS resource for exposing REST endpoints for Task manipulation
 */
@Path("demo")
public class DemoResource {

    // application instance health
    // 1 is healthy
    private static Integer health = 1;

    @GET
    @Path("load/{seconds}")
    @Produces({"application/json"})
    public String generateLoad(@Context SecurityContext context, @PathParam("seconds") int seconds) {
        Load cpuLoad = new Load();
        cpuLoad.generateLoad(seconds * 1000);
        String response = new String("Load being generated for " + seconds + " seconds.");
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.INFO, "INFO: Requested to generate load for " + seconds + " seconds.");
        return "{\"response\":\"" + response + "\"}";
    }

    @GET
    @Path("log/info")
    @Produces({"application/json"})
    public String logInfo(@Context SecurityContext context) {
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.INFO, "INFO: OpenShift 3 is an excellent platform for JEE development.");
        return new String("{\"response\":\"An informational message was recorded internally.\"}");
    }

    @GET
    @Path("log/warning")
    @Produces({"application/json"})
    public String logWarning(@Context SecurityContext context) {
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.WARNING, "WARN: Flying a kite in a thunderstorm should not be attempted.");
        return new String("{\"response\":\"A warning message was recorded internally.\"}");
    }

    @GET
    @Path("log/error/")
    @Produces({"application/json"})
    public String logSevere(@Context SecurityContext context) {
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.SEVERE, "ERROR: Something pretty bad has happened and should probably be addressed sooner or later.");
        return new String("{\"response\":\"An internal error has occured!\"}");
    }

    // this endpoint will toggle the health of this instance
    @GET
    @Path("togglehealth/")
    @Produces({"application/json"})
    public String togglehealth(@Context SecurityContext context) {
        // check if currently healthy, otherwise "become" healthy
        if (health == 1) {
            // become unhealthy
            health = 0;
            Logger log = Logger.getLogger(DemoResource.class.getName());
            log.log(Level.SEVERE, "ERROR: I'm not feeling so well.");
            return new String("{\"response\":\"The app is starting to look a little ill...\"}");
        } else {
            // become healthy
            health = 1;
            Logger log = Logger.getLogger(DemoResource.class.getName());
            log.log(Level.INFO, "INFO: I feel much better.");
            return new String("{\"response\":\"The app is starting to look great!\"}");
        }
    }

    @GET
    @Path("killswitch/")
    @Produces({"application/json"})
    public void killSwitch(@Context SecurityContext context) throws IOException {
        Logger log = Logger.getLogger(DemoResource.class.getName());
        log.log(Level.SEVERE, "ERROR: Going down NOW!");
        Runtime.getRuntime().halt(255);
    }

    @GET
    @Path("healthcheck/")
    @Produces({"application/json"})
    public Response checkHealth(@Context SecurityContext context) throws IOException {

        String response = new String("{\"response\":\"Health Status: " + health + "\", \"health\": " + health + "}");

        // if health is 1, return 200, otherwise 500
        if (health == 1) {
            return Response.status(Response.Status.OK).entity(response).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }

    }
}
