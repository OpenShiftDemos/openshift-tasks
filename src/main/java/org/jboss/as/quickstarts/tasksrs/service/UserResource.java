package org.jboss.as.quickstarts.tasksrs.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.jboss.as.quickstarts.tasksrs.model.User;
import org.jboss.as.quickstarts.tasksrs.model.UserDao;

/**
 * A JAX-RS resource for exposing REST endpoints for User manipulation
 */

@Path("/users")
public class UserResource {
    @Inject
    private UserDao userDao;

    @GET
    @Path("/")
    @Produces({ "application/xml", "application/json" })
    public List<User> getUsers() {
    	List<User> users = userDao.getAll();
    	
//		TODO: Uncomment to sort users based on number of tasks

//    	Collections.sort(users, new Comparator<User>() {
//
//			@Override
//			public int compare(User user1, User user2) {
//				return Integer.compare(user2.getTasks().size(), user1.getTasks().size());
//			}
//		});
    	
        return users;
    }
}
